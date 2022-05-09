package com.carefast.careops.repository.permission;

import com.carefast.careops.model.permission.CustomPermissionModel;
import com.carefast.careops.model.permission.PermissionModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionModel, Integer> {

    @Query(value = "SELECT p.permission_id AS permissionId, te.id_employee AS employeeId, " +
            "te.employee_code AS employeeCode, " +
            "te.employee_name AS employeeName, " +
            "te.employee_photo_profile AS employeePhotoProfile, " +
            "p.replace_by_employee_id AS replaceByEmployeeId, " +
            "p.approve_by_employee_id AS approveByEmployeeId, " +
            "p.id_detail_employee_project AS idDetailEmployeeProject, " +
            "p.project_id AS projectId, " +
            "p.status_permission AS statusPermission, " +
            "p.title AS title, p.description AS description, " +
            "p.plotting_id AS plottingId, p.at_date AS atDate, " +
            "ts.shift_description AS shiftDescription, " +
            "tds.start_at AS startAt, tds.end_at AS endAt, " +
            "tl.location_name AS locationName, " +
            "tsl.sub_location_name AS subLocationName, " +
            "p.image AS image, " +
            "p.created_at AS createdAt " +
            "FROM tab_permission p " +
            "INNER JOIN tab_employee_insys te ON p.request_by_employee_id = te.id_employee " +
            "INNER JOIN tab_shift ts ON p.shift_id = ts.shift_id " +
            "INNER JOIN tab_sub_location_area tsa ON p.plotting_id = tsa.id_sub_location_area " +
            "INNER JOIN tab_location tl ON tsa.id_location = tl.location_id " +
            "INNER JOIN tab_sub_location tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "INNER JOIN tab_detail_shift_project tds ON p.shift_id = tds.id_shift " +
            "WHERE tds.id_project = :projectId " +
            "AND p.request_by_employee_id = :employeeId " +
            "AND p.project_id = :projectId " +
            "ORDER BY p.created_at DESC ", nativeQuery = true)
    Slice<CustomPermissionModel> getAllPermission(int employeeId, String projectId, Pageable pageable);

    @Query(value = "SELECT p.permission_id AS permissionId, te.id_employee AS employeeId, " +
            "te.employee_code AS employeeCode, " +
            "te.employee_name AS employeeName, " +
            "te.employee_photo_profile AS employeePhotoProfile, " +
            "p.replace_by_employee_id AS replaceByEmployeeId, " +
            "p.approve_by_employee_id AS approveByEmployeeId, " +
            "p.id_detail_employee_project AS idDetailEmployeeProject, " +
            "p.project_id AS projectId, " +
            "p.status_permission AS statusPermission, " +
            "p.title AS title, p.description AS description, " +
            "p.plotting_id AS plottingId, p.at_date AS atDate, " +
            "ts.shift_description AS shiftDescription, " +
            "tds.start_at AS startAt, tds.end_at AS endAt, " +
            "tl.location_name AS locationName, " +
            "tsl.sub_location_name AS subLocationName, " +
            "p.image AS image, " +
            "p.created_at AS createdAt " +
            "FROM tab_permission p " +
            "INNER JOIN tab_employee_insys te ON p.request_by_employee_id = te.id_employee " +
            "INNER JOIN tab_shift ts ON p.shift_id = ts.shift_id " +
            "INNER JOIN tab_sub_location_area tsa ON p.plotting_id = tsa.id_sub_location_area " +
            "INNER JOIN tab_location tl ON tsa.id_location = tl.location_id " +
            "INNER JOIN tab_sub_location tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "INNER JOIN tab_detail_shift_project tds ON p.shift_id = tds.id_shift " +
            "WHERE tds.id_project = :projectId " +
            "AND p.approve_by_employee_id = :employeeId " +
            "AND p.project_id = :projectId " +
            "ORDER BY p.created_at DESC ", nativeQuery = true)
    Slice<CustomPermissionModel> getAllPermissionProcessApproveByEmployeeId(int employeeId, String projectId, Pageable pageable);

    @Query(value = "SELECT p.permission_id AS permissionId, te.id_employee AS employeeId, " +
            "te.employee_code AS employeeCode, " +
            "te.employee_name AS employeeName, " +
            "te.employee_photo_profile AS employeePhotoProfile, " +
            "p.replace_by_employee_id AS replaceByEmployeeId, " +
            "p.approve_by_employee_id AS approveByEmployeeId, " +
            "p.id_detail_employee_project AS idDetailEmployeeProject, " +
            "p.project_id AS projectId, " +
            "p.status_permission AS statusPermission, " +
            "p.title AS title, p.description AS description, " +
            "p.plotting_id AS plottingId, p.at_date AS atDate, " +
            "ts.shift_description AS shiftDescription, " +
            "tds.start_at AS startAt, tds.end_at AS endAt, " +
            "tl.location_name AS locationName, " +
            "tsl.sub_location_name AS subLocationName, " +
            "p.image AS image, " +
            "p.created_at AS createdAt " +
            "FROM tab_permission p " +
            "INNER JOIN tab_employee_insys te ON p.request_by_employee_id = te.id_employee " +
            "INNER JOIN tab_shift ts ON p.shift_id = ts.shift_id " +
            "INNER JOIN tab_sub_location_area tsa ON p.plotting_id = tsa.id_sub_location_area " +
            "INNER JOIN tab_location tl ON tsa.id_location = tl.location_id " +
            "INNER JOIN tab_sub_location tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "INNER JOIN tab_detail_shift_project tds ON p.shift_id = tds.id_shift " +
            "WHERE tds.id_project = p.project_id " +
            "AND p.permission_id  = :permissionId ", nativeQuery = true)
    Optional<CustomPermissionModel> getPermissionDetail(int permissionId);
}
