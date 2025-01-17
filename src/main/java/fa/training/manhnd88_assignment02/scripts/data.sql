-- Create a new database called 'certmanagement'
-- Connect to the 'master' database to run this snippet
USE master
GO
-- Create the new database if it does not exist already
IF NOT EXISTS (SELECT [name]
               FROM sys.databases
               WHERE [name] = N'certmanagement')
CREATE DATABASE certmanagement
GO

-- Inserting records into the categories table
INSERT INTO [dbo].[categories] (description, name)
VALUES ('Skills in managing Kubernetes clusters', 'CKA'),
       ('Building and maintaining AWS applications', 'AWS Developer'),
       ('Java programming skills', 'Java Programmer'),
       ('Managing large-scale projects', 'PMP'),
       ('Managing MongoDB applications', 'MongoDB Developer'),
       ('Cybersecurity best practices', 'Security+'),
       ('Proficiency in C and C++', 'C/C++ Cert'),
       ('Statistical computing with R', 'R Cert'),
       ('Developing Azure solutions', 'Azure Dev'),
       ('Building applications on Google Cloud', 'Google Cloud Dev'),
       ('Managing Microsoft applications', 'Azure Architect'),
       ('Managing Google Cloud solutions', 'Google Cloud Arch'),
       ('Data analysis with Python', 'Python Data Analyst'),
       ('Enterprise applications with Spring', 'Spring Cert'),
       ('Administering Red Hat Linux', 'RHCSA'),
       ('DevOps principles and tools', 'DevOps Engineer'),
       ('Data engineering with Google Cloud', 'Google Data Engineer'),
       ('Administering SQL databases', 'SQL Admin'),
       ('Using Apache Hadoop', 'Hadoop Cert'),
       ('Software testing principles', 'ISTQB Tester'),
       ('Systems engineering with Puppet', 'Puppet Pro'),
       ('Data science with Python', 'Python Data Sci'),
       ('Developing with Power Platform', 'Power Platform Dev'),
       ('Managing AWS infrastructure', 'AWS Arch'),
       ('Machine learning with TensorFlow', 'TensorFlow Dev'),
       ('Web development with React', 'React Cert'),
       ('Cloud computing with Azure', 'Azure Cert'),
       ('DevOps with AWS', 'AWS DevOps'),
       ('Data science with R', 'R Data Sci'),
       ('Database management with SQL', 'SQL Cert');

-- Inserting records into the certificates table
INSERT INTO [dbo].[certificates] (category_id, cost, id, name)
VALUES ((SELECT id FROM [dbo].[categories] WHERE name = 'CKA'), 300.00, 'CERT-01-01',
        'Certified Kubernetes Administrator'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'AWS Developer'), 250.00, 'CERT-01-02',
        'AWS Developer Associate'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Java Programmer'), 200.00, 'CERT-01-03',
        'Oracle Java Programmer'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'PMP'), 500.00, 'CERT-01-04',
        'Project Management Professional'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'MongoDB Developer'), 180.00, 'CERT-01-05',
        'MongoDB Developer'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Security+'), 300.00, 'CERT-01-06', 'CompTIA Security+'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'C/C++ Cert'), 150.00, 'CERT-01-07', 'C and C++ Certification'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'R Cert'), 220.00, 'CERT-01-08', 'R Programming Certification'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Azure Dev'), 240.00, 'CERT-01-09',
        'Azure Developer Associate'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Google Cloud Dev'), 280.00, 'CERT-01-10',
        'Google Cloud Developer'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Azure Architect'), 320.00, 'CERT-01-11',
        'Azure Solutions Architect'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Google Cloud Arch'), 350.00, 'CERT-01-12',
        'Google Cloud Architect'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Python Data Analyst'), 210.00, 'CERT-01-13',
        'Python Data Analyst'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Spring Cert'), 230.00, 'CERT-01-14', 'Spring Professional'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'RHCSA'), 270.00, 'CERT-01-15', 'Red Hat System Administrator'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'DevOps Engineer'), 260.00, 'CERT-01-16',
        'Certified DevOps Engineer'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Google Data Engineer'), 290.00, 'CERT-01-17',
        'Google Data Engineer'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'SQL Admin'), 200.00, 'CERT-01-18',
        'SQL Database Administrator'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Hadoop Cert'), 220.00, 'CERT-01-19', 'Hadoop Certification'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'ISTQB Tester'), 210.00, 'CERT-01-20',
        'ISTQB Certified Tester'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Puppet Pro'), 240.00, 'CERT-01-21', 'Puppet Professional'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Python Data Sci'), 230.00, 'CERT-01-22',
        'Python Data Science'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Power Platform Dev'), 220.00, 'CERT-01-23',
        'Power Platform Developer'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'AWS Arch'), 330.00, 'CERT-01-24', 'AWS Solutions Architect'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'TensorFlow Dev'), 250.00, 'CERT-01-25',
        'TensorFlow Developer'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'React Cert'), 210.00, 'CERT-01-26', 'React Certification'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'Azure Cert'), 220.00, 'CERT-01-27', 'Azure Certification'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'AWS DevOps'), 310.00, 'CERT-01-28', 'AWS DevOps'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'R Data Sci'), 200.00, 'CERT-01-29', 'R Data Science'),
       ((SELECT id FROM [dbo].[categories] WHERE name = 'SQL Cert'), 240.00, 'CERT-01-30', 'SQL Certification');



