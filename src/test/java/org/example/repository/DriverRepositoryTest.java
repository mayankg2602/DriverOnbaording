package org.example.repository;

import org.example.dataModel.DriverEntity;
import org.example.repository.DriverRepository;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DriverRepositoryTest {

    private DriverRepository driverRepository;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private org.hibernate.Session session;

    @Mock
    private Query query;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        driverRepository = new DriverRepository(sessionFactory);
    }

    @Test
    public void testSave() {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setDriverId(1L);
        driverEntity.setName("Test name");

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        doNothing().when(session).saveOrUpdate(any(DriverEntity.class));

        DriverEntity result = driverRepository.save(driverEntity);

        assertEquals(driverEntity, result);
        verify(sessionFactory).getCurrentSession();
        verify(session).saveOrUpdate(driverEntity);
    }


    @Test
    public void testFindById() {
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setDriverId(1L);
        driverEntity.setName("Test name");

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.get(DriverEntity.class, 1L)).thenReturn(driverEntity);

        Optional<DriverEntity> result = driverRepository.findById(1L);

        assertEquals(driverEntity, result.get());
        verify(sessionFactory).getCurrentSession();
        verify(session).get(DriverEntity.class, 1L);
    }
}
