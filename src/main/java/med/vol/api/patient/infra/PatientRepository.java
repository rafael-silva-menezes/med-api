package med.vol.api.patient.infra;

import med.vol.api.patient.domain.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("""
    select p.active from Patient p
    where p.id = :id
""")
    Boolean findActiveById(Long id);
}