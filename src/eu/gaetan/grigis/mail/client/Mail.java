package eu.gaetan.grigis.mail.client;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Mail {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
    private String id;
	@Persistent String subject;
	@Persistent String from;
	@Persistent String to;
	@Persistent String content;
	
	public Mail(String subject,String from,String to,String content)
	{
		this.subject=subject;
		this.from   =from;
		this.to		=to;
		this.content=content;
	}
}
