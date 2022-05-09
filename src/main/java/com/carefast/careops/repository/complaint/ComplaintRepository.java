package com.carefast.careops.repository.complaint;

import com.carefast.careops.model.complaint.ComplaintModel;
import com.carefast.careops.model.complaint.CustomComplaintModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<ComplaintModel, Integer> {
//    @Query("SELECT c FROM ComplaintModel c " +
//            "WHERE c.clientId = :clientId " +
//            "AND c.projectId = :projectId " +
//            "ORDER BY c.createdAt DESC")
//    List<ComplaintModel> getAllUserComplaint(int clientId, String projectId);

    @Query(value = "SELECT tc.complaint_id as complaintId, " +
            "tc.client_id AS clientId," +
            "c.client_name AS clientName, " +
            "tc.project_id AS projectId, " +
            "tc.title AS title, " +
            "tc.description AS description, " +
            "tc.image AS image, " +
            "tc.location_id AS locationId, " +
            "tl.location_name AS locationName, " +
            "tc.sub_location_id AS subLocationId, " +
            "tsl.sub_location_name AS subLocationName, " +
            "tc.process_by AS processBy, " +
            "tc.worker_id AS workerId, " +
            "tc.before_image AS beforeImage, " +
            "tc.process_image AS processImage, " +
            "tc.after_image AS afterImage, " +
            "tc.status_complaint AS statusComplaint, " +
            "tc.created_at AS createdAt, " +
            "DATE(tc.created_at) AS date, " +
            "TIME(tc.created_at) AS time " +
            "FROM tab_complaint AS tc " +
            "INNER JOIN tab_client AS c ON tc.client_id = c.client_id " +
            "INNER JOIN tab_location AS tl ON tc.location_id = tl.location_id " +
            "INNER JOIN tab_sub_location AS tsl ON tc.sub_location_id = tsl.sub_location_id " +
            "WHERE tc.client_id = :clientId " +
            "AND tc.project_id = :projectId " +
            "ORDER BY tc.created_at DESC ", nativeQuery = true)
    Slice<CustomComplaintModel> getAllUserComplaint(int clientId, String projectId, Pageable pageable);

    @Query(value = "SELECT tc.complaint_id as complaintId, " +
            "tc.client_id AS clientId," +
            "c.client_name AS clientName, " +
            "te.id_employee AS createdByEmployeeId, " +
            "te.employee_name AS createdByEmployeeName, " +
            "tc.project_id AS projectId, " +
            "tc.title AS title, " +
            "tc.description AS description, " +
            "tc.image AS image, " +
            "tc.location_id AS locationId, " +
            "tl.location_name AS locationName, " +
            "tc.sub_location_id AS subLocationId, " +
            "tsl.sub_location_name AS subLocationName, " +
            "tc.process_by AS processBy, " +
            "tc.worker_id AS workerId, " +
            "tc.before_image AS beforeImage, " +
            "tc.process_image AS processImage, " +
            "tc.after_image AS afterImage, " +
            "tc.status_complaint AS statusComplaint, " +
            "tc.created_at AS createdAt, " +
            "DATE(tc.created_at) AS date, " +
            "TIME(tc.created_at) AS time " +
            "FROM tab_complaint AS tc " +
            "LEFT JOIN tab_client AS c ON tc.client_id = c.client_id " +
            "LEFT JOIN tab_employee_insys AS te ON tc.created_by_employee_id = te.id_employee " +
            "INNER JOIN tab_location AS tl ON tc.location_id = tl.location_id " +
            "INNER JOIN tab_sub_location AS tsl ON tc.sub_location_id = tsl.sub_location_id " +
            "WHERE tc.created_by_employee_id = :employeeId " +
            "AND tc.project_id = :projectId " +
            "ORDER BY tc.created_at DESC ", nativeQuery = true)
    Slice<CustomComplaintModel> getAllComplaintByEmployeeId(int employeeId, String projectId, Pageable pageable);

    @Query(value = "SELECT tc.complaint_id as complaintId, " +
            "tc.client_id AS clientId," +
            "c.client_name AS clientName, " +
            "te.id_employee AS createdByEmployeeId, " +
            "te.employee_name AS createdByEmployeeName, " +
            "tc.project_id AS projectId, " +
            "tc.title AS title, " +
            "tc.description AS description, " +
            "tc.image AS image, " +
            "tc.location_id AS locationId, " +
            "tl.location_name AS locationName, " +
            "tc.sub_location_id AS subLocationId, " +
            "tsl.sub_location_name AS subLocationName, " +
            "tc.process_by AS processBy, " +
            "tc.worker_id AS workerId, " +
            "tc.before_image AS beforeImage, " +
            "tc.process_image AS processImage, " +
            "tc.after_image AS afterImage, " +
            "tc.status_complaint AS statusComplaint, " +
            "tc.created_at AS createdAt, " +
            "DATE(tc.created_at) AS date, " +
            "TIME(tc.created_at) AS time " +
            "FROM tab_complaint AS tc " +
            "LEFT JOIN tab_client AS c ON tc.client_id = c.client_id " +
            "LEFT JOIN tab_employee_insys AS te ON tc.created_by_employee_id = te.id_employee " +
            "INNER JOIN tab_location AS tl ON tc.location_id = tl.location_id " +
            "INNER JOIN tab_sub_location AS tsl ON tc.sub_location_id = tsl.sub_location_id " +
            "WHERE tc.complaint_id = :complaintId ", nativeQuery = true)
    Optional<CustomComplaintModel> getDetailComplaint(int complaintId);

    @Query(value = "SELECT tc.complaint_id as complaintId, " +
            "tc.client_id AS clientId," +
            "c.client_name AS clientName, " +
            "te.id_employee AS createdByEmployeeId, " +
            "te.employee_name AS createdByEmployeeName, " +
            "tc.project_id AS projectId, " +
            "tc.title AS title, " +
            "tc.description AS description, " +
            "tc.image AS image, " +
            "tc.location_id AS locationId, " +
            "tl.location_name AS locationName, " +
            "tc.sub_location_id AS subLocationId, " +
            "tsl.sub_location_name AS subLocationName, " +
            "tc.process_by AS processBy, " +
            "tc.worker_id AS workerId, " +
            "tc.before_image AS beforeImage, " +
            "tc.process_image AS processImage, " +
            "tc.after_image AS afterImage, " +
            "tc.status_complaint AS statusComplaint, " +
            "tc.created_at AS createdAt, " +
            "DATE(tc.created_at) AS date, " +
            "TIME(tc.created_at) AS time " +
            "FROM tab_complaint AS tc " +
            "LEFT JOIN tab_client AS c ON tc.client_id = c.client_id " +
            "LEFT JOIN tab_employee_insys AS te ON tc.created_by_employee_id = te.id_employee " +
            "INNER JOIN tab_location AS tl ON tc.location_id = tl.location_id " +
            "INNER JOIN tab_sub_location AS tsl ON tc.sub_location_id = tsl.sub_location_id " +
            "WHERE tc.project_id = :projectId " +
            "ORDER BY tc.created_at DESC ", nativeQuery = true)
    Slice<CustomComplaintModel> getComplaintByProjectCode(String projectId, Pageable pageable);
}
