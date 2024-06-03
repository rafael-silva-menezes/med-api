package med.vol.api.infra.repositories;

import med.vol.api.domain.entities.Doctor;
import med.vol.api.domain.entities.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
                    select d from Doctor d
                    where d.active = true
                    and d.specialization = :specialization
                    and d.id not in (
                    select a.doctor.id from Appointment a
                    where a.date = :date
            )
            order by rand()
            limit 1
            """)
    Doctor choiceDoctorAvailable(Specialization specialization, LocalDateTime date);

    @Query("""
    select d.active from Doctor d
    where d.id = :id
""")
    Boolean findActiveById(Long id);
}