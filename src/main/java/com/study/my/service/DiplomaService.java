package com.study.my.service;

import com.study.my.dao.DaoFactory;
import com.study.my.dao.DiplomaDao;
import com.study.my.model.Diploma;
import org.apache.log4j.Logger;

public class DiplomaService {

    private static final Logger LOGGER = Logger.getLogger(DiplomaService.class);
    private DiplomaDao diplomaDao;

    public DiplomaService() {
        diplomaDao = DaoFactory.getInstance().createDiplomaDao();
    }

    public boolean save(Diploma diploma) {
        if (diploma.getId() == null) {
            Diploma created = diplomaDao.create(diploma);
            LOGGER.debug("Creating diploma diploma: " + diploma + " result: " + created);
            return created != null;
        } else {
            boolean updated = diplomaDao.update(diploma);
            LOGGER.debug("Updating diploma diploma: " + diploma + " result: " + updated);
            return updated;
        }
    }
}
