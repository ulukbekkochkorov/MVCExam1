package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public interface CompanyRepository {
    public List<Company> getAllCompanies();
    void addCompany(Company company);
    Company getCompanyById(Long id);
    void updateCompany(Company company);
    void deleteByIdCompany(Long id);

}
