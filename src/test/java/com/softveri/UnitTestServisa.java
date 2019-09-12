package com.softveri;


import com.dental.entity.DentalService;
import com.dental.entity.Dentist;
import com.dental.entity.Patient;
import com.dental.entity.Treatement;
import com.dental.entity.User;
import com.dental.service.impl.DentalServiceImpl;
import com.dental.service.impl.DentistServiceImpl;
import com.dental.service.impl.PatientServiceImpl;
import com.dental.service.impl.TreatementServiceImpl;
import com.dental.service.impl.ToothServiceImpl;
import com.dental.service.impl.UserServiceImpl;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;




@RunWith(MockitoJUnitRunner.class)
public class UnitTestServisa {
	
	@Mock
	TreatementServiceImpl treatmantService;	
	
	@Mock
	ToothServiceImpl toothService;
	
	@Mock
	PatientServiceImpl patientService;
	
	@Mock
	DentalServiceImpl dentalService;
        
        @Mock
	DentistServiceImpl dentistService;
        
         @Mock
	UserServiceImpl userService;

	
    @Test
    public void findOneDentalServiceTest() throws Exception
    {
    	Mockito.when(dentalService.findOne(new Long("1"))).thenReturn(new DentalService(new Long("1"), "Lokalna anestezija", 0, 0));         
        DentalService dental = dentalService.findOne(new Long("1"));         
        assertEquals("Lokalna anestezija", dental.getName());
        
        System.out.println("Uspesno vracen ID dental service!");
       
    }
    
    @Test
    public void findAllDentalServiceTest() {
  
    	List<DentalService> dental = new ArrayList<DentalService>();
    	DentalService d1 = new DentalService(new Long("3"), "Izbeljivanje zuba", 100, 0);
        DentalService d2 = new DentalService(new Long("4"), "Anestezija", 150, 0);

        dental.add(d1);
        dental.add(d2);
         
        Mockito.when(dentalService.findAll()).thenReturn(dental);
         
        //test
        
        List<DentalService> dentalList = dentalService.findAll();         
        assertEquals(2, dentalList.size());
        Mockito.verify(dentalService, Mockito.times(1)).findAll();

        System.out.println("Uspesno vracena lista!");
    }
    
   
      @Test
    public void findOneDentistServiceTest() throws Exception
    {
    	
        Mockito.when(dentistService.findOne(new Long("34"))).thenReturn(new Dentist(new Long("1"), "Pera", "Peric", "muski", "pera", "pera123"));
        Dentist dentist = dentistService.findOne(new Long("34"));         
        assertEquals("Pera", dentist.getFirstname());
        
        System.out.println("Uspesno vracen ID zubara!");
       
    }
    @Test
    public void saveDentistTest() throws Exception
    {
    	Dentist dentist = new Dentist();
            try {
                dentistService.save(dentist);
            } catch (Exception ex) {
                Logger.getLogger(UnitTestServisa.class.getName()).log(Level.SEVERE, null, ex);
            }
        Mockito.verify(dentistService, Mockito.times(1)).save(dentist);

        System.out.println("Uspesno sacuvan zubar!");
    }
   
    @Test
    public void findAllDentistsTest()
    {
    	List<Dentist> dentists = new ArrayList<Dentist>();
    	Dentist d1 = new Dentist(new Long("1"), "Katarina", "Djordjevic", "female", "kaca", "kaca123");
    	Dentist d2 = new Dentist(new Long("2"), "Pera", "Peric", "male", "pera", "pera123");
    	Dentist d3 = new Dentist(new Long("3"), "Zika", "Zikic", "male", "zika", "zika123");
         
        dentists.add(d1);
        dentists.add(d2);
        dentists.add(d3);

         
        Mockito.when(dentistService.findAll()).thenReturn(dentists);
         
        //test
        
        List<Dentist> dentistsList = dentistService.findAll();         
        assertEquals(3, dentistsList.size());
        Mockito.verify(dentistService, Mockito.times(1)).findAll();

        System.out.println("Uspesno vracena lista!");
    }
    
    @Test
    public void findOneTreatmentTest()
    {
    	
            try {
                Mockito.when(treatmantService.findOne(new Long("43"))).thenReturn(new Treatement(new Long("43"), Date.valueOf("2019-09-02"), "", 0));
                Treatement t = treatmantService.findOne(new Long("43"));
                assertEquals(new Double("0"), t.getCost());
                assertEquals("", t.getNote());
                
                System.out.println("Uspesno vracen ID pregleda!");
            } catch (Exception ex) {
                Logger.getLogger(UnitTestServisa.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
    @Test
    public void saveTreatmentTest()
    {
            try {
                User user = userService.findUser("pera", "pera123");
                Treatement t = new Treatement(new Long("1"), Date.valueOf("2019-09-02"), "", 0);
                treatmantService.save(t, user);
                Mockito.verify(treatmantService, Mockito.times(1)).save(t, user);
                
                System.out.println("Uspesno sacuvan pregled!");
            } catch (Exception ex) {
                Logger.getLogger(UnitTestServisa.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @Test
    public void findAllTreatment(){
    
            try {
                List<Treatement> treatments = new ArrayList<Treatement>();
                Treatement t1 = new Treatement(new Long("3"), Date.valueOf("2019-09-05"), "", 0);
                Treatement t2 = new Treatement(new Long("4"), Date.valueOf("2019-09-06"), "", 0);
                Treatement t3 = new Treatement(new Long("5"), Date.valueOf("2019-09-07"), "", 0);
                
                treatments.add(t1);
                treatments.add(t2);
                treatments.add(t3);
                
                
                Mockito.when(treatmantService.findAll()).thenReturn(treatments);
                
                //test
                
                List<Treatement> treatmentsList = treatmantService.findAll();
                assertEquals(3, treatmentsList.size());
                Mockito.verify(treatmantService, Mockito.times(1)).findAll();
            } catch (Exception ex) {
                Logger.getLogger(UnitTestServisa.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    
    }
      @Test
    public void findOnePatientTest()
    {
    	
            try {
                Mockito.when(patientService.findOne(new Long("2"))).thenReturn(new Patient(new Long("2"), "Ana", "Milosevic", "", "", "", Date.valueOf("1970-08-25")));
                Patient p = patientService.findOne(new Long("2"));
                assertEquals(new Long("2"), p.getPatientID());
                assertEquals("Ana", p.getFirstname());
                
                System.out.println("Uspesno vracen ID pacijenta!");
            } catch (Exception ex) {
                Logger.getLogger(UnitTestServisa.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
    @Test
    public void savePatientTest()
    {
            try {
                Patient p =  new Patient(new Long("2"), "Joca", "Jocic", "", "", "", Date.valueOf("1995-05-08"));
                patientService.save(p);
                Mockito.verify(patientService, Mockito.times(1)).save(p);
                
                System.out.println("Uspesno sacuvan pacijent!");
            } catch (Exception ex) {
                Logger.getLogger(UnitTestServisa.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    @Test
    public void findAllPatients(){
    
            try {
                List<Patient> patients = new ArrayList<Patient>();
               
                Patient p1 =  new Patient(new Long("2"), "Joca", "Jocic", "", "", "", Date.valueOf("1995-05-08"));
                Patient p2 =  new Patient(new Long("3"), "Joca", "Jovanovic", "", "", "", Date.valueOf("1998-09-09"));

               patients.add(p1);
               patients.add(p2);
                
                Mockito.when(patientService.findAll()).thenReturn(patients);
                
                //test
                
                List<Patient> patientList = patientService.findAll();
                assertEquals(2, patientList.size());
                Mockito.verify(patientService, Mockito.times(1)).findAll();
            } catch (Exception ex) {
                Logger.getLogger(UnitTestServisa.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    
    }
}
