package com.miniproject.krs.config;


import com.miniproject.krs.entity.FakultasEntity;
import com.miniproject.krs.entity.JurusanEntity;
import com.miniproject.krs.entity.RoleEntity;
import com.miniproject.krs.entity.UserEntity;
import com.miniproject.krs.repository.FakultasRepo;
import com.miniproject.krs.service.RoleService;
import com.miniproject.krs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DbInit implements CommandLineRunner {
    private FakultasRepo fakultasRepo;
    private PasswordEncoder encoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DbInit(FakultasRepo fakultasRepo, PasswordEncoder encoder, UserService userService, RoleService roleService) {
        this.fakultasRepo = fakultasRepo;
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    private void initFakultas(){
        if(fakultasRepo.count() == 0) {
            FakultasEntity fakultas = new FakultasEntity("FMIPA", "Fakultas Matematika dan IPA", "Yogyakarta");
            fakultas.addJurusan(new JurusanEntity("MTK", "Matematika"));
            fakultas.addJurusan(new JurusanEntity("FIK", "Fisika"));
            fakultas.addJurusan(new JurusanEntity("BIO", "Biologi"));
            fakultas.addJurusan(new JurusanEntity("SNC", "Since"));

            this.fakultasRepo.save(fakultas);

            FakultasEntity fakultas2 = new FakultasEntity("FK", "Kedokteran", "Yogyakarta");
            fakultas2.addJurusan(new JurusanEntity("KG", "Kedokteran Gigi"));
            fakultas2.addJurusan(new JurusanEntity("KJ", "Kedokteran Jiwa"));
            fakultas2.addJurusan(new JurusanEntity("KH", "Kedokteran Hewan"));
            fakultas2.addJurusan(new JurusanEntity("KC", "Kedokteran Cinta"));

            this.fakultasRepo.save(fakultas2);
        }
    }

    private void initUserAndRole(){
        if(roleService.getCount() == 0){
            this.roleService.save(Arrays.asList(
                    new RoleEntity("ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_DOSEN"),
                    new RoleEntity("ROLE_MAHASISWA"),
                    new RoleEntity("ROLE_KEUANGAN")
            ));
        }

        if(userService.getCount()==0){
            RoleEntity adminRole = roleService.getByName("ROLE_ADMIN");
            UserEntity admin = new UserEntity("admin", encoder.encode("admin"),"admin@gmail.com", Arrays.asList(adminRole));
            this.userService.save(admin);

            RoleEntity mhsRole = roleService.getByName("ROLE_MAHASISWA");
            UserEntity mhs = new UserEntity("templates/mahasiswa", encoder.encode("mahasiswa"),"mahasiswa@gmail.com", Arrays.asList(mhsRole));
            this.userService.save(mhs);

            RoleEntity dosenRole = roleService.getByName("ROLE_DOSEN");
            UserEntity dosen = new UserEntity("templates/dosen", encoder.encode("dosen"),"dosen@gmail.com", Arrays.asList(dosenRole));
            this.userService.save(dosen);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        initFakultas();
        // panggil method generate role user
        initUserAndRole();
    }
}
