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
}
