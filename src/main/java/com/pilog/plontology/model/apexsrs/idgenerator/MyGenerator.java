package com.pilog.plontology.model.apexsrs.idgenerator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MyGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor arg0, Object arg1) throws HibernateException {
		String date = new SimpleDateFormat("yyyy").format(new Date());
		int num = new Random().nextInt(100000);
		String Prefix1 = "DOC-";
		return Prefix1 + date + "-" + num;
	}

}
