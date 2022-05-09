package com.carefast.careops.repository.overtime;

import com.carefast.careops.model.overtime.CustomOvertimeGantiModel;
import com.carefast.careops.model.overtime.OvertimeModel;
import com.carefast.careops.model.permission.CustomPermissionModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OvertimeRepository extends JpaRepository<OvertimeModel, Integer> {
    @Query(value = "SELECT o.overtime_id  AS overtimeId, " +
            "o.employee_id AS employeeId, te1.employee_code AS employeeCode, te1.employee_name AS employeeName, te1.employee_photo_profile AS employeePhotoProfile, " +
            "o.employee_replace_id AS employeeReplaceId, te.employee_code AS employeeReplaceCode, te.employee_name AS employeeReplaceName, te.employee_photo_profile AS employeeReplacePhotoProfile, " +
            "o.created_by_employee_id AS createdByEmployeeId, te2.employee_code AS employeeCodeCreated, te2.employee_name AS employeeNameCreated, te2.employee_photo_profile AS employeePhotoProfileCreated, " +
            "o.id_detail_employee_project AS idDetailEmployeeProject, " +
            "o.project_id AS projectId, o.status AS status, o.title AS title, o.description AS description, " +
            "o.plotting_id AS plottingId, o.at_date AS atDate, ts.shift_description AS shiftDescription, " +
            "tds.start_at AS startAt, tds.end_at AS endAt, tl.location_name AS locationName, " +
            "tsl.sub_location_name AS subLocationName, o.image AS image, o.permission_id AS permissionId, " +
            "o.is_permission AS isPermission, o.created_at AS createdAt " +
            "FROM tab_overtime o " +
            "INNER JOIN tab_employee_insys te ON o.employee_replace_id = te.id_employee " +
            "INNER JOIN tab_employee_insys te1 ON o.employee_id = te1.id_employee " +
            "INNER JOIN tab_employee_insys te2 ON o.created_by_employee_id = te2.id_employee " +
            "INNER JOIN tab_shift ts ON o.shift_id = ts.shift_id " +
            "INNER JOIN tab_sub_location_area tsa ON o.plotting_id = tsa.id_sub_location_area " +
            "INNER JOIN tab_location tl ON tsa.id_location = tl.location_id " +
            "INNER JOIN tab_sub_location tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "INNER JOIN tab_detail_shift_project tds ON o.shift_id = tds.id_shift " +
            "WHERE tds.id_project = :projectId " +
            "AND o.employee_id = :employeeId " +
            "AND o.project_id = :projectId " +
            "ORDER BY o.created_at DESC ", nativeQuery = true)
    Slice<CustomOvertimeGantiModel> getAllOvertimeGanti(int employeeId, String projectId, Pageable pageable);

    @Query(value = "SELECT o.overtime_id  AS overtimeId, " +
            "o.employee_id AS employeeId, te1.employee_code AS employeeCode, te1.employee_name AS employeeName, te1.employee_photo_profile AS employeePhotoProfile, " +
            "o.employee_replace_id AS employeeReplaceId, te.employee_code AS employeeReplaceCode, te.employee_name AS employeeReplaceName, te.employee_photo_profile AS employeeReplacePhotoProfile, " +
            "o.created_by_employee_id AS createdByEmployeeId, te2.employee_code AS employeeCodeCreated, te2.employee_name AS employeeNameCreated, te2.employee_photo_profile AS employeePhotoProfileCreated, " +
            "o.id_detail_employee_project AS idDetailEmployeeProject, " +
            "o.project_id AS projectId, o.status AS status, o.title AS title, o.description AS description, " +
            "o.plotting_id AS plottingId, o.at_date AS atDate, ts.shift_description AS shiftDescription, " +
            "tds.start_at AS startAt, tds.end_at AS endAt, tl.location_name AS locationName, " +
            "tsl.sub_location_name AS subLocationName, o.image AS image, o.permission_id AS permissionId, " +
            "o.is_permission AS isPermission, o.created_at AS createdAt " +
            "FROM tab_overtime o " +
            "INNER JOIN tab_employee_insys te ON o.employee_replace_id = te.id_employee " +
            "INNER JOIN tab_employee_insys te1 ON o.employee_id = te1.id_employee " +
            "INNER JOIN tab_employee_insys te2 ON o.created_by_employee_id = te2.id_employee " +
            "INNER JOIN tab_shift ts ON o.shift_id = ts.shift_id " +
            "INNER JOIN tab_sub_location_area tsa ON o.plotting_id = tsa.id_sub_location_area " +
            "INNER JOIN tab_location tl ON tsa.id_location = tl.location_id " +
            "INNER JOIN tab_sub_location tsl ON tsa.sub_location_id = tsl.sub_location_id " +
            "INNER JOIN tab_detail_shift_project tds ON o.shift_id = tds.id_shift " +
            "WHERE o.overtime_id = :overtimeId " +
            "AND tds.id_project = o.project_id", nativeQuery = true)
    Optional<CustomOvertimeGantiModel> getOvertimeGantiDetail(int overtimeId);
}
