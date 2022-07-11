package exam.management.be.service;

import exam.management.be.model.Speciality;
import exam.management.be.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecialityService implements BaseService<Speciality> {
    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public Iterable<Speciality> findAll() {
        return specialityRepository.findAll();
    }

    @Override
    public Optional<Speciality> findById(Integer id) {
        return specialityRepository.findById(id);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    @Override
    public Speciality update(Speciality speciality) {
        return specialityRepository.saveAndFlush(speciality);
    }

    @Override
    public void remove(Integer id) {
        specialityRepository.deleteById(id);
    }

    public Iterable<Speciality> save(Iterable<Speciality> specialities) {
        return specialityRepository.saveAll(specialities);
    }
}
