@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {

    private static final Logger logger = LogManager.getLogger(EntrepriseServiceImpl.class);

    @Autowired
    ITimesheetService ts;

    @Test
    public void testajouterMission(){

        logger.info("Creation d'une mission");
        Mission mission = new Mission("Encadrer", "Encadrer les PFEs");
        logger.info("Ajout dans la base");
        assertEquals(mission.getName(), ts.ajouterMission(mission).getName());
    }

    @Test
    public void testajouterTimesheet(){

        logger.info("Creation d'une Timesheet");
        TimesheetPK timesheetPK = new TimesheetPK();
        timesheetPK.setDateDebut(new Date());
        timesheetPK.setDateFin(new Date());
        timesheetPK.setIdEmploye(1);
        timesheetPK.setIdMission(1);

        Timesheet timesheet = new Timesheet();
        timesheet.setTimesheetPK(timesheetPK);
        timesheet.setValide(false); //par defaut non valide

        assertEquals(timesheet.getMission(), ts.ajouterTimesheet(1, 1, new Date(), new Date()).getMission());
    }

    @Test
    public void testfindAllMissionByEmployeJPQL(){

        List<Mission> missions = ts.findAllMissionByEmployeJPQL(1);
        assertTrue(missions.size()<10);
    }

    @Test
    public void testgetAllEmployeByMission(){
        List<Employe> employees = ts.getAllEmployeByMission(1);
        assertTrue(employees.size()<10);
        //assertEquals(0, emloyees.size());
        //test
    }








}