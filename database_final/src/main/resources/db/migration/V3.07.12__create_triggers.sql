-- Function to update `Total_Enrollments` in `Course` table when a new enrollment is inserted
CREATE OR REPLACE FUNCTION update_total_enrollments_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Course
    SET Total_Enrollments = Total_Enrollments + 1
    WHERE ID = NEW.Course_ID;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Trigger to execute the function `update_total_enrollments_on_insert()` after an INSERT on `Enrollment`
CREATE TRIGGER after_enrollment_insert
    AFTER INSERT ON Enrollment
    FOR EACH ROW
    EXECUTE FUNCTION update_total_enrollments_on_insert();


-- Function to update `Total_Enrollments` in `Course` table when an enrollment is deleted
CREATE OR REPLACE FUNCTION update_total_enrollments_on_delete()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Course
    SET Total_Enrollments = Total_Enrollments - 1
    WHERE ID = OLD.Course_ID;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;
-- Trigger to execute the function `update_total_enrollments_on_delete()` after a DELETE on `Enrollment`
CREATE TRIGGER after_enrollment_delete
    AFTER DELETE ON Enrollment
    FOR EACH ROW
    EXECUTE FUNCTION update_total_enrollments_on_delete();


-- Function to update `AverageRating` in `Course` table when feedback is inserted
CREATE OR REPLACE FUNCTION update_average_rating_on_insert()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Course
    SET Average_Rating = (
        SELECT COALESCE(AVG(Rating), 0)
        FROM Feedback
        WHERE Course_ID = NEW.Course_ID
    )
    WHERE ID = NEW.Course_ID;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Trigger to execute the function `update_average_rating_on_insert()` after an INSERT on `Feedback`
CREATE TRIGGER after_feedback_insert
    AFTER INSERT ON Feedback
    FOR EACH ROW
    EXECUTE FUNCTION update_average_rating_on_insert();


-- Function to update `AverageRating` in `Course` table when feedback is deleted
CREATE OR REPLACE FUNCTION update_average_rating_on_delete()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Course
    SET Average_Rating = (
        SELECT COALESCE(AVG(Rating), 0)
        FROM Feedback
        WHERE Course_ID = OLD.Course_ID
    )
    WHERE ID = OLD.Course_ID;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;
-- Trigger to execute the function `update_average_rating_on_delete()` after a DELETE on `Feedback`
CREATE TRIGGER after_feedback_delete
    AFTER DELETE ON Feedback
    FOR EACH ROW
    EXECUTE FUNCTION update_average_rating_on_delete();


