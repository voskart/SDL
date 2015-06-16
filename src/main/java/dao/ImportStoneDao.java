package dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import model.ImportStone;


@Repository
public class ImportStoneDao extends GenericDao<ImportStone, Long>{
	
	public ImportStoneDao() {
		super.setInstanceClass(ImportStone.class);
	}
	
	public List<ImportStone> getStonebyMaterial(String str){
		Query query = super.getEntityManager().createQuery("SELECT r FROM ImportStone r WHERE r.Material LIKE :Id")
				  .setParameter("Id", str);
		if (query.getResultList().size()==0){
			return null;
		}
		return query.getResultList();
	}
}
