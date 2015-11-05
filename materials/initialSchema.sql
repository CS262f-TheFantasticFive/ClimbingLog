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
	-- Unique identifier, ignore case
	-- i.e. 'davejoshmike'
	ID varchar(50) PRIMARY KEY,
	
	-- password must be encrypted, no less than 6 characters, case sensitive
	password varchar(50), -- ENCRYPTED
	
	-- Email used to login (can be NULL if using facebook?)
	emailAddress varchar(50),
	
	-- first and last name seperated by a comma
	-- i.e. 'Michel, David'
	name varchar(50),
	
	-- store multiple userIDs in one string seperated by commas? 
	-- i.e. myFriends = 'davejoshmike, cpd5, jam'
	myFriends varchar(500), 
	
	-- Member since: dateCreated
	dateCreated timestamp 
	);

CREATE TABLE Climb (
	-- unique identifier
	ID integer PRIMARY KEY,
	
	-- name given by user
	name varchar(50),
	
	-- color selected by user (default = 'white')
	color varchar(50),
	
	-- i.e. '5.8+', 'V7', or '5.10a' 
	difficulty varchar(50),
	
	-- i.e. 'boulder' 'top rope'
	type varchar(50),
	
	-- a paragraph of notes (500 character limit)
	notes varchar(500),
	
	-- where the climb took place
	location varchar(50),
	
	-- when the climb happened (initially auto-generated, but editable by the user)
	time timestamp
	);

-- Linking table for Climber and Climb tables
CREATE TABLE ClimberClimb (
	climberID varchar(50) REFERENCES Climber(ID),
	climbID varchar(50) REFERENCES Climb(ID)
	);

-- Allow users to select data from the tables.
GRANT SELECT ON Climber TO PUBLIC;
GRANT SELECT ON Climb TO PUBLIC;
GRANT SELECT ON ClimberClimb TO PUBLIC;

-- Add sample records.
INSERT INTO Climber VALUES ('davejoshmike', 'funthings8', 'davejoshmike@gmail.com', 'Michel, David', 'jam, cpd5', '2015-10-27 18:10:00');
