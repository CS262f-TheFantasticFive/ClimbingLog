----------------------------------------------------------------
-- This SQL script builds the SQL tables for our Climbing App --
----------------------------------------------------------------
-- @author David Michel ----------------------------------------
-- @version 27 October 2015 ------------------------------------
-- SUBJECT TO CHANGE -------------------------------------------
----------------------------------------------------------------

-- Drop previous versions of the tables if they they exist, in reverse order of foreign keys.
DROP TABLE IF EXISTS ClimberClimb;
DROP TABLE IF EXISTS Climb;
DROP TABLE IF EXISTS Climber;

-- Create the schema.
CREATE TABLE Climber (
	ID varchar(50) PRIMARY KEY, -- Unique identifier, ignore case (i.e. 'davejoshmike')
	password varchar(50), -- ENCRYPTED. password must be encrypted, no less than 6 characters, case sensitive
	emailAddress varchar(50), -- Email used to login (can be NULL if using facebook?)
	name varchar(50), -- first and last name seperated by a comma
	myFriends varchar(500), -- store multiple userIDs in one string seperated by commas? i.e. myFriends = 'davejoshmike, cpd5, jam'
	dateCreated timestamp -- Member since: dateCreated
	);

CREATE TABLE Climb (
	ID integer PRIMARY KEY, -- unique identifier
	name varchar(50), -- name given by user
	color varchar(50), -- color selected by user (default = 'white')
	difficulty varchar(50), -- i.e. '5.8+', 'V7', or '5.10a'
	type varchar(50), -- i.e. 'boulder' 'top rope'
	notes varchar(500), -- a paragraph of notes (500 character limit)
	location varchar(50), -- where the climb took place
	time timestamp -- when the climb happened (initially auto-generated, but editable by the user)
	);

-- Linking table for Climber and Climb tables
CREATE TABLE ClimberClimb (
	climberID varchar(50) REFERENCES Climber(ID),
	climbID integer REFERENCES Climb(ID)
	);

-- Allow users to select data from the tables.
GRANT SELECT ON Climber TO PUBLIC;
GRANT SELECT ON Climb TO PUBLIC;
GRANT SELECT ON ClimberClimb TO PUBLIC;

-- Add sample records.
INSERT INTO Climber VALUES ('davejoshmike', 'funthings8', 'davejoshmike@gmail.com', 'Michel, David', 'jam, cpd5', '2015-10-27 18:10:00');

-- Test queries

-- Lists the names of all climbers and how many days they've been a member
SELECT Climber.name as Name, 
       date_part('day', NOW() - Climber.dateCreated) as Member_days
FROM Climber

