INSERT INTO [dbo].[categories] ([description], [name])
VALUES
    ('Programming language', 'Java'), ('Programming language', 'JavaScript'), ('Framework', '.NET'), ('Frontend framework', 'Angular'), ('Programming language', 'Python');

-- Assuming you want to generate random strings for the IDs
INSERT INTO [dbo].[certificates] ([id], [cert_name], [cost], [description], [category_id])
VALUES
    (LEFT (NEWID(), 12), 'Java SE 11 Developer', 150.00, 'Oracle Certified Java SE 11 Developer', 1), (LEFT (NEWID(), 12), 'JavaScript Specialist', 100.00, 'Certified JavaScript Developer', 2), (LEFT (NEWID(), 12), '.NET Core Developer', 120.00, 'Microsoft Certified .NET Core Developer', 3), (LEFT (NEWID(), 12), 'Angular Developer', 110.00, 'Certified Angular Developer', 4), (LEFT (NEWID(), 12), 'Python Developer', 130.00, 'Certified Python Developer', 5), (LEFT (NEWID(), 12), 'Advanced JavaScript', 140.00, 'Advanced concepts in JavaScript', 2), (LEFT (NEWID(), 12), 'Spring Framework', 125.00, 'Spring Framework Certification', 1), (LEFT (NEWID(), 12), 'React Developer', 115.00, 'Certified React Developer', 2), (LEFT (NEWID(), 12), 'ASP.NET Developer', 135.00, 'Certified ASP.NET Developer', 3), (LEFT (NEWID(), 12), 'Python Data Science', 145.00, 'Python for Data Science Certification', 5);

