package com.example.tingeso;

import com.example.tingeso.entities.AutorizacionEntity;
import com.example.tingeso.repositories.AutorizacionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutorizacionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    @Test
    void testFindBy(){
        AutorizacionEntity autorizacion1 = new AutorizacionEntity();
        autorizacion1.setFecha("2022/04/18");
        autorizacion1.setId(80);
        autorizacion1.setRut("20.537.567-8");
        entityManager.merge(autorizacion1);

        AutorizacionEntity autorizacion2 = autorizacionRepository.findByRut(autorizacion1.getRut());
        assertThat(autorizacion2.getRut()).isEqualTo(autorizacion1.getRut());
    }


}
