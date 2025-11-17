package org.centrale.hceres.repository;

import org.centrale.hceres.items.NationalInternationalCollaboration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalInternationalCollaborationRepository extends JpaRepository<NationalInternationalCollaboration, Integer> {

}
