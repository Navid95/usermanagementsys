package navid.usermanagementsys.bootstrap;

import navid.usermanagementsys.domain.*;
import navid.usermanagementsys.service.JpaService.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class JpaBootStrap implements ApplicationListener<ApplicationReadyEvent> {

    private User admin, user;

    private Personnel p1, p2, p3;

    private Company company1, company2, company3, company4;

    private Permission CREATE_ROLE, READ_ROLE, UPDATE_ROLE, DELETE_ROLE, READ_PERMISSION, CREATE_PERSONEL, READ_PERSONEL, UPDATE_PERSONEL, DELETE_PERSONEL, CREATE_COMPANY, READ_COMPANY, UPDATE_COMPANY, DELETE_COMPANY, CREATE_USER, READ_USER, UPDATE_USER, DELETE_USER;

    private Role ROLE_ADMIN, ROLE_USER;

    private Field bodyBuilding, pilates, track_and_field;

    private TimeTable timeTable1, timeTable2, timeTable3;

    private Saloon saloon1, saloon2, saloon3;

    private Gym gym1, gym2;

    private Logger logger = LogManager.getLogger(JpaBootStrap.class);

//    ************************************* Services ********************************************************

    private UserService userService;

    private GymService gymService;

    private RoleService roleService;

    private PermissionService permissionService;

    private CompanyService companyService;

    private PersonnelService personnelService;

    private FieldService fieldService;


    private TimeTableService timeTableService;

    private SaloonService saloonService;


    @Autowired
    public void setSaloonService(SaloonService saloonService) {
        this.saloonService = saloonService;
    }

    @Autowired
    public void setTimeTableService(TimeTableService timeTableService) {
        this.timeTableService = timeTableService;
    }

    @Autowired
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Autowired
    public void setPersonnelService(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }


    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    public void setGymService(GymService gymService) {
        this.gymService = gymService;
    }

//    ************************************  Boot Straping Data ****************************************************

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        loadPermissions();
        loadRoles();
        loadUsers();
        loadPersonel();
        loadCompanys();
        loadFields();
        loadGyms();
        loadSaloons();
        loadTimeTables();
    }

//      ***************************************************************************************************

    public void loadPermissions() {

        CREATE_ROLE = loadOrCreatePermission(CREATE_ROLE, "CREATE_ROLE", "ROLE");
        READ_ROLE = loadOrCreatePermission(READ_ROLE, "READ_ROLE", "ROLE");
        UPDATE_ROLE = loadOrCreatePermission(UPDATE_ROLE, "UPDATE_ROLE", "ROLE");
        DELETE_ROLE = loadOrCreatePermission(DELETE_ROLE, "DELETE_ROLE", "ROLE");

        READ_PERMISSION = loadOrCreatePermission(READ_PERMISSION, "READ_PERMISSION", "PERMISSION");

        CREATE_PERSONEL = loadOrCreatePermission(CREATE_PERSONEL, "CREATE_PERSONNEL", "PERSONNEL");
        READ_PERSONEL = loadOrCreatePermission(READ_PERSONEL, "READ_PERSONNEL", "PERSONNEL");
        UPDATE_PERSONEL = loadOrCreatePermission(UPDATE_PERSONEL, "UPDATE_PERSONNEL", "PERSONNEL");
        DELETE_PERSONEL = loadOrCreatePermission(DELETE_PERSONEL, "DELETE_PERSONNEL", "PERSONNEL");

        CREATE_COMPANY = loadOrCreatePermission(CREATE_COMPANY, "CREATE_COMPANY", "COMPANY");
        READ_COMPANY = loadOrCreatePermission(READ_COMPANY, "READ_COMPANY", "COMPANY");
        UPDATE_COMPANY = loadOrCreatePermission(UPDATE_COMPANY, "UPDATE_COMPANY", "COMPANY");
        DELETE_COMPANY = loadOrCreatePermission(DELETE_COMPANY, "DELETE_COMPANY", "COMPANY");

        CREATE_USER = loadOrCreatePermission(CREATE_USER, "CREATE_USER", "USER");
        READ_USER = loadOrCreatePermission(READ_USER, "READ_USER", "USER");
        UPDATE_USER = loadOrCreatePermission(UPDATE_USER, "UPDATE_USER", "USER");
        DELETE_USER = loadOrCreatePermission(DELETE_USER, "DELETE_USER", "USER");

    }

    public Permission loadOrCreatePermission(Permission permission, String permissionName, String objectName) {
        if (permissionService.findByName(permissionName) == null) {
            permission = new Permission();
            permission.setName(permissionName);
            permission.setDescription(permissionName);
            permission.setObjectName(objectName);
            permissionService.saveOrUpdate(permission);
            logger.warn(permissionName + " permission created !!!");
        } else {
            permission = permissionService.findByName(permissionName);
            logger.warn(permissionName + " permission loaded !!!");
        }
        return permission;
    }

//      ***************************************************************************************************

    public void loadRoles() {

        List<Permission> admin_permissions = new ArrayList<>();
        admin_permissions.add(CREATE_ROLE);
        admin_permissions.add(READ_ROLE);
        admin_permissions.add(UPDATE_ROLE);
        admin_permissions.add(DELETE_ROLE);

        admin_permissions.add(READ_PERMISSION);

        admin_permissions.add(CREATE_COMPANY);
        admin_permissions.add(READ_COMPANY);
        admin_permissions.add(UPDATE_COMPANY);
        admin_permissions.add(DELETE_COMPANY);

        admin_permissions.add(CREATE_PERSONEL);
        admin_permissions.add(READ_PERSONEL);
        admin_permissions.add(UPDATE_PERSONEL);
        admin_permissions.add(DELETE_PERSONEL);

        admin_permissions.add(CREATE_USER);
        admin_permissions.add(READ_USER);
        admin_permissions.add(UPDATE_USER);
        admin_permissions.add(DELETE_USER);

        ROLE_ADMIN = loadOrCreateRole(ROLE_ADMIN, "ROLE_ADMIN", admin_permissions);

//            *****************************************************************************************

        List<Permission> user_permissions = new ArrayList<>();
        user_permissions.add(READ_ROLE);
        user_permissions.add(READ_USER);

        ROLE_USER = loadOrCreateRole(ROLE_USER, "ROLE_USER", user_permissions);

//            *****************************************************************************************

    }

    public Role loadOrCreateRole(Role role, String role_name, List<Permission> permissions) {
        if (roleService.findByRoleName(role_name) == null) {
            role = new Role();
            role.setRoleName(role_name);
            role.setPermissions(permissions);
            roleService.saveOrUpdate(role);
            logger.warn(role_name + " created !!!");
        } else {
            role = roleService.findByRoleName(role_name);
            logger.warn(role_name + " loaded !!!");
        }
        return role;
    }

//      ***************************************************************************************************

    public void loadUsers() {
        if (userService.findByUsername("admin") == null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");

            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(ROLE_ADMIN);
            admin.setRoles(adminRoles);
            userService.saveOrUpdate(admin);
//            System.out.println("Admin pass : "+admin.getPassword());
//            logger.info("admin created !!!");
        } else {
            admin = userService.findByUsername("admin");
            logger.warn("admin loaded !!!");
        }

        if (userService.findByUsername("user") == null) {
            user = new User();
            user.setUsername("user");
            user.setPassword("user");
            List<Role> userRoles = new ArrayList<>();
            userRoles.add(ROLE_USER);
            user.setRoles(userRoles);
            userService.saveOrUpdate(user);
//            System.out.println("user pass : "+user.getPassword());
            logger.info("user created !!!");
        } else {
            user = userService.findByUsername("user");
            logger.warn("user loaded !!!");
//            System.out.println("user pass : "+user.getPassword());
        }

    }

//      ***************************************************************************************************

    public void loadGyms() {

        gym1 = loadOrCreateGym(gym1, "gym1", admin, null);

        gym2 = loadOrCreateGym(gym2, "gym2", new User("gym2" , "gym2" , new ArrayList<Role>()), null);
    }

    public Gym loadOrCreateGym(Gym gym, String gym_name, User admin, List<Saloon> saloons) {
        if (gymService.findByName(gym_name) == null) {
            gym = new Gym();
            gym.setName(gym_name);
            gym.setAdmin(admin);
            gym.setAddress("Tehran");
            if (saloons != null)
                saloons.add(new Saloon());
            gym.setSaloons(saloons);
            gymService.saveOrUpdate(gym);
            logger.warn(gym_name + " gym created !!!");
        } else {
            gym = gymService.findByName(gym_name);
            logger.warn(gym_name + " gym loaded !!!");
        }
        return gym;
    }

//      ***************************************************************************************************

    public void loadCompanys() {

        if (companyService.findByName("company1") == null) {
            List<Personnel> personnels = new ArrayList<>();
            List<Role> roles = new ArrayList<>();
            User comp_admin = new User();
            comp_admin.setUsername("company");
            comp_admin.setPassword("company");
            roles.add(ROLE_ADMIN);
            comp_admin.setRoles(roles);

            company1 = new Company();
            company1.setAddress("IRAN");
            company1.setName("company1");
            company1.setDescription("Description Description Description Description");
            company1.setAdmin(comp_admin);
            company1.setAdminName("Company admin");
            company1.addPersonel(p1);
            company1.addPersonel(p2);
            companyService.saveOrUpdate(company1);
            logger.warn("company1 created !!!");
        } else {
            company1 = companyService.findByName("company1");
            logger.warn("company1 loaded !!!");
        }

        loadOrCreateCompany(company2, "سلام");

    }

    private void loadOrCreateCompany(Company company, String name) {
        if (companyService.findByName(name) == null) {
            List<Personnel> personnels = new ArrayList<>();
            List<Role> roles = new ArrayList<>();
            User comp_admin = new User();
            comp_admin.setUsername(name);
            comp_admin.setPassword(name);
            roles.add(ROLE_ADMIN);
            comp_admin.setRoles(roles);
            company = new Company();
            company.setAddress("IRAN");
            company.setName(name);
            company.setDescription("Description Description Description Description");
            company.setAdmin(comp_admin);
            company.setAdminName("Company admin");
//            company.addPersonel(p1);
//            company.addPersonel(p2);
            companyService.saveOrUpdate(company);
            logger.warn(name + " created !!!");
        } else {
            company = companyService.findByName(name);
            logger.warn(name + " loaded !!!");
        }
    }

//      ***************************************************************************************************

    public void loadPersonel() {

        if (personnelService.findByUsername("p1") == null) {
            p1 = new Personnel();
            p1.setFirstName("p1");
            p1.setLastName("P1");
            p1.setBirthDate(LocalDate.now());
            p1.setUsername("p1");
            p1.setPassword("p1");
            p1.setNationalId(1234);
            List<Role> roles = new ArrayList<>();
            roles.add(ROLE_USER);
            p1.setRoles(roles);
            personnelService.saveOrUpdate(p1);
            logger.warn("p1 personel created!!!");
        } else {
            p1 = personnelService.findByUsername("p1");
            logger.warn("p1 personel loaded!!!");
        }
        if (personnelService.findByUsername("p2") == null) {
            p2 = new Personnel();
            p2.setFirstName("p2");
            p2.setLastName("p2");
            p2.setBirthDate(LocalDate.now());
            p2.setUsername("p2");
            p2.setPassword("p2");
            p2.setNationalId(1234);
            List<Role> roles = new ArrayList<>();
            roles.add(ROLE_USER);
            p2.setRoles(roles);
            personnelService.saveOrUpdate(p2);
            logger.warn("p2 personel created!!!");
        } else {
            p2 = personnelService.findByUsername("p2");
            logger.warn("p2 personel loaded!!!");
        }
    }

//      ***************************************************************************************************

    public void loadFields() {
        bodyBuilding = loadOrCreateField(bodyBuilding, "bodyBuilding");
        pilates = loadOrCreateField(pilates, "pilates");
        track_and_field = loadOrCreateField(track_and_field, "track_and_field");
    }

    public Field loadOrCreateField(Field field, String field_name) {

        if (fieldService.findByFieldName(field_name) == null) {

            field = new Field();
            field.setFieldName(field_name);
            field = fieldService.saveOrUpdate(field);
            logger.warn(field_name + " field created!!!");

        } else {

            field = fieldService.findByFieldName(field_name);
            logger.warn(field_name + " field loaded!!!");
        }
        return field;
    }

//      ***************************************************************************************************

    public void loadTimeTables() {
        timeTable1 = loadOrCreateTimeTable(timeTable1, OffsetTime.now(), OffsetTime.now(), bodyBuilding, Gender.MALE , saloon1);
        timeTable2 = loadOrCreateTimeTable(timeTable2, OffsetTime.now(), OffsetTime.now(), pilates, Gender.FEMALE , saloon2);
        timeTable3 = loadOrCreateTimeTable(timeTable3, OffsetTime.now(), OffsetTime.now(), track_and_field, Gender.MALE , saloon3);
    }

    public TimeTable loadOrCreateTimeTable(TimeTable timeTable, OffsetTime startTime, OffsetTime endTime, Field field, Gender gender , Saloon saloon) {

        if (timeTableService.findByStartTimeEqualsAndEndTimeEqualsAndDayOfWeekEqualsAndSaloon
                (startTime, endTime, DayOfWeek.of(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)),
                        saloon) == null) {
            timeTable = new TimeTable();
            timeTable.setDayOfWeek(DayOfWeek.of(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)));
            timeTable.setGender(gender);
            timeTable.setField(field);
            timeTable.setSaloon(saloon);
            timeTable.setStartTime(startTime);
            timeTable.setEndTime(endTime);
            timeTable = timeTableService.saveOrUpdate(timeTable);
            logger.warn("timetable created!!!");

        } else {
            timeTable = timeTableService.findByStartTimeEqualsAndEndTimeEqualsAndDayOfWeekEqualsAndSaloon(startTime, endTime, DayOfWeek.of(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)), saloon);
            logger.warn("timetable loaded!!!");
        }
        return timeTable;
    }

//      ***************************************************************************************************

    public void loadSaloons() {

        saloon1 = loadOrCreateSaloon(saloon1, "saloon1", gym1, null);
        saloon2 = loadOrCreateSaloon(saloon2, "saloon2", gym1, null);
        saloon3 = loadOrCreateSaloon(saloon3, "saloon3", gym2, null);

    }

    public Saloon loadOrCreateSaloon(Saloon saloon, String saloon_name, Gym gym, List<TimeTable> timeTables) {
        if (saloonService.findBySaloonNameAndGym(saloon_name , gym) == null) {
            saloon = new Saloon();
            saloon.setCapacity(50);
            saloon.setSaloonName(saloon_name);
            saloon.setGym(gym);
            saloon.setTimeTables(timeTables);
            saloon = saloonService.saveOrUpdate(saloon);
            logger.warn(saloon_name + " saloon created!!!");
        } else {
            saloon = saloonService.findBySaloonName(saloon_name);
            logger.warn(saloon_name + " saloon loaded!!!");
        }
        return saloon;
    }

//      ***************************************************************************************************

}