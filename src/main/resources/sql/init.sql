IF OBJECT_ID('dbo.ProcessingQueue', 'U') IS NOT NULL DROP TABLE dbo.ProcessingQueue;
IF OBJECT_ID('dbo.JobHistory', 'U') IS NOT NULL DROP TABLE dbo.JobHistory;
IF OBJECT_ID('dbo.JobFiles', 'U') IS NOT NULL DROP TABLE dbo.JobFiles;
IF OBJECT_ID('dbo.ConversionJob', 'U') IS NOT NULL DROP TABLE dbo.ConversionJob;
IF OBJECT_ID('dbo.JobFile', 'U') IS NOT NULL DROP TABLE dbo.JobFile;
IF OBJECT_ID('dbo.AppUser', 'U') IS NOT NULL DROP TABLE dbo.AppUser;
IF OBJECT_ID('dbo.JobStatus', 'U') IS NOT NULL DROP TABLE dbo.JobStatus;
IF OBJECT_ID('dbo.Role', 'U') IS NOT NULL DROP TABLE dbo.Role;

CREATE TABLE dbo.Role (
    role_id INT IDENTITY(1,1) PRIMARY KEY,
    role_name NVARCHAR(50) UNIQUE NOT NULL,
    max_requests INT,
    max_files INT
);

CREATE TABLE dbo.AppUser (
    user_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    email NVARCHAR(255) UNIQUE NOT NULL,
    password_hash NVARCHAR(255) NOT NULL,
    created_at DATETIME2 DEFAULT SYSUTCDATETIME(),
    role_id INT NOT NULL,
    CONSTRAINT FK_user_role FOREIGN KEY (role_id) REFERENCES dbo.Role(role_id)
);

CREATE TABLE dbo.JobStatus (
    status_id INT IDENTITY(1,1) PRIMARY KEY,
    status_name NVARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE dbo.ConversionJob (
    job_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    input_format NVARCHAR(50) NOT NULL,
    output_format NVARCHAR(50) NOT NULL,
    status_id INT,
    created_at DATETIME2 DEFAULT SYSUTCDATETIME(),
    completed_at DATETIME2 NULL,
    CONSTRAINT FK_conversion_job_user FOREIGN KEY (user_id) REFERENCES dbo.AppUser(user_id),
    CONSTRAINT FK_conversion_job_status FOREIGN KEY (status_id) REFERENCES dbo.JobStatus(status_id)
);

CREATE TABLE dbo.JobFile (
    job_file_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    file_name NVARCHAR(255) NOT NULL,
    storage_path NVARCHAR(512) NOT NULL,
    uploaded_at DATETIME2 DEFAULT SYSUTCDATETIME()
);

CREATE TABLE dbo.JobFiles (
    job_files_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    job_id BIGINT,
    job_file_id BIGINT,
    CONSTRAINT FK_job_files_job FOREIGN KEY (job_id) REFERENCES dbo.ConversionJob(job_id) ON DELETE SET NULL,
    CONSTRAINT FK_job_files_file FOREIGN KEY (job_file_id) REFERENCES dbo.JobFile(job_file_id) ON DELETE SET NULL
);

CREATE TABLE dbo.ProcessingQueue (
    queue_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    job_id BIGINT NOT NULL,
    attempts INT DEFAULT 0,
    status_id INT NOT NULL,
    created_at DATETIME2 DEFAULT SYSUTCDATETIME(),
    CONSTRAINT FK_processing_queue_job FOREIGN KEY (job_id) REFERENCES dbo.ConversionJob(job_id),
    CONSTRAINT FK_processing_queue_status FOREIGN KEY (status_id) REFERENCES dbo.JobStatus(status_id)
);

CREATE TABLE dbo.JobHistory (
    history_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    job_id BIGINT NOT NULL,
    old_status_id INT NOT NULL,
    new_status_id INT NOT NULL,
    changed_at DATETIME2 DEFAULT SYSUTCDATETIME(),
    CONSTRAINT FK_job_history_job FOREIGN KEY (job_id) REFERENCES dbo.ConversionJob(job_id),
    CONSTRAINT FK_job_history_old_status FOREIGN KEY (old_status_id) REFERENCES dbo.JobStatus(status_id),
    CONSTRAINT FK_job_history_new_status FOREIGN KEY (new_status_id) REFERENCES dbo.JobStatus(status_id)
);

CREATE INDEX idx_conversion_job_status ON dbo.ConversionJob(status_id);
CREATE INDEX idx_processing_queue_status ON dbo.ProcessingQueue(status_id);

INSERT INTO dbo.Role (role_name, max_requests, max_files)
VALUES
    ('Admin', NULL, NULL),
    ('User', 20, 5),
    ('Premium User', 10000, 30);

INSERT INTO dbo.JobStatus (status_name)
VALUES
    ('Pending'),
    ('Processing'),
    ('Completed'),
    ('Failed');
