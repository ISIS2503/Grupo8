package utils;

import com.avaje.ebean.Model;

public abstract class ModelDB extends Model
{
	protected String server;

	@Override
	public void save( )
	{
		super.insert( server );
	}

	@Override
	public void update( )
	{
		super.update( server );
	}

	protected void setServer(String server)
	{
		this.server = server;
	}
}
