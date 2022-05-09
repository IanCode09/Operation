package com.carefast.careops.complaint.repository;

import com.carefast.careops.model.complaint.CustomComplaintModel;
import com.carefast.careops.repository.complaint.ComplaintRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ComplaintRepositoryTest {

    @Autowired
    ComplaintRepository complaintRepository;

    @Test
    @Disabled
    void testGetDetailComplaint() {
        Optional<CustomComplaintModel> complaint = complaintRepository.getDetailComplaint(1);

        System.out.println(complaint.get().getComplaintId());
        System.out.println(complaint.get().getClientId());
        System.out.println(complaint.get().getClientName());
    }

}
