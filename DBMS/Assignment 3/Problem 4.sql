
/********************************************************************
 * ASSIGNMENT 4: Tables and Query for Zip Code, City, and State
 *
 * Create 3 separate tables: State, City, and ZipCode.
 ********************************************************************/

/* State Table */
CREATE TABLE State (
    StateID INT AUTO_INCREMENT PRIMARY KEY,
    StateName VARCHAR(100) NOT NULL
);

/* City Table */
CREATE TABLE City (
    CityID INT AUTO_INCREMENT PRIMARY KEY,
    CityName VARCHAR(100) NOT NULL,
    StateID INT NOT NULL,
    FOREIGN KEY (StateID) REFERENCES State(StateID)
        ON DELETE CASCADE
);

/* ZipCode Table */
CREATE TABLE ZipCode (
    ZipCode VARCHAR(10) PRIMARY KEY,
    CityID INT NOT NULL,
    FOREIGN KEY (CityID) REFERENCES City(CityID)
        ON DELETE CASCADE
);

/* Query to return Zip Code, City Names, and States ordered by State Name and City Name */
SELECT 
    z.ZipCode, 
    c.CityName, 
    s.StateName
FROM ZipCode z
JOIN City c ON z.CityID = c.CityID
JOIN State s ON c.StateID = s.StateID
ORDER BY s.StateName, c.CityName;

