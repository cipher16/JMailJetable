package eu.gaetan.grigis.mail.shared;

import java.util.Date;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class User {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String id;
	@Persistent
	private String name;
	@Persistent Date date;
	
	public User(String name)
	{
		this.setName(name);
		this.date=new Date();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
