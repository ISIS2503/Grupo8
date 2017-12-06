import models.main.Actuador;
import models.main.Alerta;
import models.main.Area;
import models.main.Dato;
import models.main.Microcontrolador;
import models.main.Nivel;
import models.main.Sensor;
import models.main.VariableAmbiental;
import models.users.Rol;
import models.users.Usuario;
import play.Application;
import play.GlobalSettings;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.Calendar;
import java.util.Collections;

public class Global extends GlobalSettings
{
	@Override
	public void onStart( Application app )
	{
		if( Rol.find.all( ).isEmpty( ) )
		{
			Rol adminRol = new Rol( );
			adminRol.setId( 1L );
			adminRol.setName( "ADMIN" );
			adminRol.insert( "usersdb" );

			Rol sysoRol = new Rol( );
			sysoRol.setId( 2L );
			sysoRol.setName( "SYSO" );
			sysoRol.insert( "usersdb" );

			Rol userRol = new Rol( );
			userRol.setId( 3L );
			userRol.setName( "USER" );
			userRol.insert( "usersdb" );

			Rol supervisorRol = new Rol( );
			supervisorRol.setId( 4L );
			supervisorRol.setName( "SUPERVISOR" );
			supervisorRol.insert( "usersdb" );

			Rol bridgeRol = new Rol( );
			bridgeRol.setId( 5L );
			bridgeRol.setName( "BRIDGE" );
			bridgeRol.insert( "usersdb" );
		}

		if( Usuario.find.all( ).isEmpty( ) )
		{
			Rol adminRol = new Rol( );
			adminRol.setId( 1L );
			Usuario admin = new Usuario( );
			admin.setLogin( "admin" );
			admin.setPassword( "admin" );
			admin.setRoles( Collections.singletonList( adminRol ) );
			admin.insert( "usersdb" );

			Rol sysoRol = new Rol( );
			sysoRol.setId( 2L );
			Usuario syso = new Usuario( );
			syso.setLogin( "syso" );
			syso.setPassword( "syso" );
			syso.setRoles( Collections.singletonList( sysoRol ) );
			syso.insert( "usersdb" );

			Rol userRol = new Rol( );
			userRol.setId( 3L );
			Usuario user = new Usuario( );
			user.setLogin( "user" );
			user.setPassword( "user" );
			user.setRoles( Collections.singletonList( userRol ) );
			user.insert( "usersdb" );

			Rol supervisorRol = new Rol( );
			supervisorRol.setId( 4L );
			Usuario supervisor = new Usuario( );
			supervisor.setLogin( "supervisor" );
			supervisor.setPassword( "supervisor" );
			supervisor.setRoles( Collections.singletonList( supervisorRol ) );
			supervisor.insert( "usersdb" );

			Rol bridgeRol = new Rol( );
			bridgeRol.setId( 5L );
			Usuario bridge = new Usuario( );
			bridge.setLogin( "bridge" );
			bridge.setPassword( "bridge123" );
			bridge.setRoles( Collections.singletonList( bridgeRol ) );
			bridge.insert( "usersdb" );
		}

		if( Nivel.find.all( ).isEmpty( ) )
		{
			Nivel nivel1 = new Nivel( );
			nivel1.setId( 1L );
			nivel1.setNivel( 1 );
			nivel1.save( );

			Nivel nivel2 = new Nivel( );
			nivel2.setId( 2L );
			nivel2.setNivel( 2 );
			nivel2.save( );

			Nivel nivel3 = new Nivel( );
			nivel3.setId( 3L );
			nivel3.setNivel( 3 );
			nivel3.save( );
		}

		if( Area.find.all( ).isEmpty( ) )
		{
			Nivel n1 = new Nivel( );
			n1.setId( 1L );
			Nivel n2 = new Nivel( );
			n2.setId( 2L );
			Nivel n3 = new Nivel( );
			n3.setId( 3L );

			Area area1 = new Area( );
			area1.setId( 1L );
			area1.setTipo( 1 );
			area1.setNivel( n1 );
			area1.save( );

			Area area2 = new Area( );
			area2.setId( 2L );
			area2.setTipo( 1 );
			area2.setNivel( n1 );
			area2.save( );

			Area area3 = new Area( );
			area3.setId( 3L );
			area3.setTipo( 1 );
			area3.setNivel( n1 );
			area3.save( );

			Area area4 = new Area( );
			area4.setId( 4L );
			area4.setTipo( 1 );
			area4.setNivel( n1 );
			area4.save( );

			Area area5 = new Area( );
			area5.setId( 5L );
			area5.setTipo( 1 );
			area5.setNivel( n2 );
			area5.save( );

			Area area6 = new Area( );
			area6.setId( 6L );
			area6.setTipo( 1 );
			area6.setNivel( n2 );
			area6.save( );

			Area area7 = new Area( );
			area7.setId( 7L );
			area7.setTipo( 1 );
			area7.setNivel( n2 );
			area7.save( );

			Area area8 = new Area( );
			area8.setId( 8L );
			area8.setTipo( 1 );
			area8.setNivel( n2 );
			area8.save( );

			Area area9 = new Area( );
			area9.setId( 9L );
			area9.setTipo( 1 );
			area9.setNivel( n3 );
			area9.save( );

			Area area10 = new Area( );
			area10.setId( 10L );
			area10.setTipo( 1 );
			area10.setNivel( n3 );
			area10.save( );

			Area area11 = new Area( );
			area11.setId( 11L );
			area11.setTipo( 1 );
			area11.setNivel( n3 );
			area11.save( );

			Area area12 = new Area( );
			area12.setId( 12L );
			area12.setTipo( 1 );
			area12.setNivel( n3 );
			area12.save( );
		}

		System.out.println( "Size: " + Microcontrolador.find.all( ).size( ) );

		if( Microcontrolador.find.all( ).isEmpty( ) )
		{
			Area a1 = new Area( );
			a1.setId( 1L );
			Area a2 = new Area( );
			a2.setId( 2L );
			Area a3 = new Area( );
			a3.setId( 3L );
			Area a4 = new Area( );
			a4.setId( 4L );

			Microcontrolador m1 = new Microcontrolador( );
			m1.setId( 1L );
			m1.setArea( a1 );
			m1.save( );

			Microcontrolador m2 = new Microcontrolador( );
			m2.setId( 2L );
			m2.setArea( a1 );
			m2.save( );

			Microcontrolador m3 = new Microcontrolador( );
			m3.setId( 3L );
			m3.setArea( a1 );
			m3.save( );

			Microcontrolador m4 = new Microcontrolador( );
			m4.setId( 4L );
			m4.setArea( a2 );
			m4.save( );

			Microcontrolador m5 = new Microcontrolador( );
			m5.setId( 5L );
			m5.setArea( a2 );
			m5.save( );

			Microcontrolador m6 = new Microcontrolador( );
			m6.setId( 6L );
			m6.setArea( a2 );
			m6.save( );

			Microcontrolador m7 = new Microcontrolador( );
			m7.setId( 7L );
			m7.setArea( a2 );
			m7.save( );

			Microcontrolador m8 = new Microcontrolador( );
			m8.setId( 8L );
			m8.setArea( a2 );
			m8.save( );

			Microcontrolador m9 = new Microcontrolador( );
			m9.setId( 9L );
			m9.setArea( a2 );
			m9.save( );
		}

		System.out.println( "Size: " + Microcontrolador.find.all( ) );

		if( VariableAmbiental.find.all( ).isEmpty( ) )
		{
			VariableAmbiental v1 = new VariableAmbiental( );
			v1.setId( 1L );
			v1.setNombre( "Temperatura" );
			v1.setFrecuencia( 60F );
			v1.setValorMaximo( 21.5F );
			v1.setValorMinimo( 27.0F );
			v1.setPrecision( 2F );
			v1.setUniadadDeMedida( "°C" );
			v1.setVariacion( 5.4F );
			v1.save( );

			VariableAmbiental v2 = new VariableAmbiental( );
			v2.setId( 2L );
			v2.setNombre( "Ruido" );
			v2.setFrecuencia( 120F );
			v2.setValorMaximo( 85F );
			v2.setValorMinimo( 0F );
			v2.setPrecision( 2F );
			v2.setUniadadDeMedida( "Decibel" );
			v2.setVariacion( 0F );
			v2.save( );

			VariableAmbiental v3 = new VariableAmbiental( );
			v3.setId( 3L );
			v3.setNombre( "Gases (Monóxido de Carbono)" );
			v3.setFrecuencia( 60F );
			v3.setValorMaximo( 100F );
			v3.setValorMinimo( 0F );
			v3.setPrecision( 2F );
			v3.setUniadadDeMedida( "ppm" );
			v3.setVariacion( 2F );
			v3.save( );

			VariableAmbiental v4 = new VariableAmbiental( );
			v4.setId( 4L );
			v4.setNombre( "Iluminación" );
			v4.setFrecuencia( 120F );
			v4.setValorMaximo( 2000F );
			v4.setValorMinimo( 100F );
			v4.setPrecision( 2F );
			v4.setUniadadDeMedida( "Lux" );
			v4.setVariacion( 0F );
			v4.save( );
		}

		if( Sensor.find.all( ).isEmpty( ) )
		{
			VariableAmbiental v1 = new VariableAmbiental( );
			v1.setId( 1L );
			VariableAmbiental v2 = new VariableAmbiental( );
			v2.setId( 2L );
			VariableAmbiental v3 = new VariableAmbiental( );
			v3.setId( 3L );
			VariableAmbiental v4 = new VariableAmbiental( );
			v4.setId( 4L );

			Microcontrolador m1 = new Microcontrolador( );
			m1.setId( 1L );
			Microcontrolador m2 = new Microcontrolador( );
			m2.setId( 2L );
			Microcontrolador m3 = new Microcontrolador( );
			m3.setId( 3L );
			Microcontrolador m4 = new Microcontrolador( );
			m4.setId( 4L );

			Sensor s1 = new Sensor( );
			s1.setId( 1L );
			s1.setTipo( v1 );
			s1.setMinimo( 0F );
			s1.setMaximo( 100F );
			s1.setMicrocontrolador( m1 );
			s1.save( );

			Sensor s2 = new Sensor( );
			s2.setId( 2L );
			s2.setTipo( v2 );
			s2.setMinimo( 0F );
			s2.setMaximo( 100F );
			s2.setMicrocontrolador( m1 );
			s2.save( );

			Sensor s3 = new Sensor( );
			s3.setId( 3L );
			s3.setTipo( v3 );
			s3.setMinimo( 0F );
			s3.setMaximo( 100F );
			s3.setMicrocontrolador( m1 );
			s3.save( );

			Sensor s4 = new Sensor( );
			s4.setId( 4L );
			s4.setTipo( v4 );
			s4.setMinimo( 0F );
			s4.setMaximo( 100F );
			s4.setMicrocontrolador( m1 );
			s4.save( );

			Sensor s5 = new Sensor( );
			s5.setId( 5L );
			s5.setTipo( v1 );
			s5.setMinimo( 0F );
			s5.setMaximo( 100F );
			s5.setMicrocontrolador( m2 );
			s5.save( );

			Sensor s6 = new Sensor( );
			s6.setId( 6L );
			s6.setTipo( v2 );
			s6.setMinimo( 0F );
			s6.setMaximo( 100F );
			s6.setMicrocontrolador( m2 );
			s6.save( );

			Sensor s7 = new Sensor( );
			s7.setId( 7L );
			s7.setTipo( v3 );
			s7.setMinimo( 0F );
			s7.setMaximo( 100F );
			s7.setMicrocontrolador( m2 );
			s7.save( );

			Sensor s8 = new Sensor( );
			s8.setId( 8L );
			s8.setTipo( v4 );
			s8.setMinimo( 0F );
			s8.setMaximo( 100F );
			s8.setMicrocontrolador( m2 );
			s8.save( );

			Sensor s9 = new Sensor( );
			s9.setId( 9L );
			s9.setTipo( v1 );
			s9.setMinimo( 0F );
			s9.setMaximo( 100F );
			s9.setMicrocontrolador( m3 );
			s9.save( );

			Sensor s10 = new Sensor( );
			s10.setId( 10L );
			s10.setTipo( v2 );
			s10.setMinimo( 0F );
			s10.setMaximo( 100F );
			s10.setMicrocontrolador( m3 );
			s10.save( );

			Sensor s11 = new Sensor( );
			s11.setId( 11L );
			s11.setTipo( v3 );
			s11.setMinimo( 0F );
			s11.setMaximo( 100F );
			s11.setMicrocontrolador( m3 );
			s11.save( );

			Sensor s13 = new Sensor( );
			s13.setId( 12L );
			s13.setTipo( v1 );
			s13.setMinimo( 0F );
			s13.setMaximo( 100F );
			s13.setMicrocontrolador( m4 );
			s13.save( );

			Sensor s14 = new Sensor( );
			s14.setId( 13L );
			s14.setTipo( v2 );
			s14.setMinimo( 0F );
			s14.setMaximo( 100F );
			s14.setMicrocontrolador( m4 );
			s14.save( );

			Sensor s15 = new Sensor( );
			s15.setId( 14L );
			s15.setTipo( v3 );
			s15.setMinimo( 0F );
			s15.setMaximo( 100F );
			s15.setMicrocontrolador( m4 );
			s15.save( );

			Sensor s16 = new Sensor( );
			s16.setId( 15L );
			s16.setTipo( v4 );
			s16.setMinimo( 0F );
			s16.setMaximo( 100F );
			s16.setMicrocontrolador( m4 );
			s16.save( );

			Sensor s12 = new Sensor( );
			s12.setId( 17L );
			s12.setTipo( v4 );
			s12.setMinimo( 0F );
			s12.setMaximo( 100F );
			s12.setMicrocontrolador( m3 );
			s12.save( );
		}

		if( Dato.find.all( ).isEmpty( ) )
		{
			Sensor s1 = new Sensor( );
			s1.setId( 1L );
			Sensor s2 = new Sensor( );
			s2.setId( 2L );
			Sensor s3 = new Sensor( );
			s3.setId( 3L );
			Sensor s4 = new Sensor( );
			s4.setId( 4L );

			Dato d1 = new Dato( );
			d1.setId( 1L );
			d1.setTimeStamp( Calendar.getInstance( ).getTime( ) );
			d1.setSensor( s1 );
			d1.save( );

			models.main.Dato d2 = new models.main.Dato( );
			d2.setId( 2L );
			d2.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d2.setSensor( s1 );
			d2.save( );

			models.main.Dato d3 = new models.main.Dato( );
			d3.setId( 3L );
			d3.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d3.setSensor( s1 );
			d3.save( );

			models.main.Dato d4 = new models.main.Dato( );
			d4.setId( 4L );
			d4.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d4.setSensor( s1 );
			d4.save( );

			models.main.Dato d5 = new models.main.Dato( );
			d5.setId( 5L );
			d5.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d5.setSensor( s1 );
			d5.save( );

			models.main.Dato d6 = new models.main.Dato( );
			d6.setId( 6L );
			d6.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d6.setSensor( s1 );
			d6.save( );

			models.main.Dato d7 = new models.main.Dato( );
			d7.setId( 7L );
			d7.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d7.setSensor( s1 );
			d7.save( );

			models.main.Dato d8 = new models.main.Dato( );
			d8.setId( 8L );
			d8.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d8.setSensor( s1 );
			d8.save( );

			models.main.Dato d9 = new models.main.Dato( );
			d9.setId( 9L );
			d9.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d9.setSensor( s1 );
			d9.save( );

			models.main.Dato d10 = new models.main.Dato( );
			d10.setId( 10L );
			d10.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d10.setSensor( s1 );
			d10.save( );

			models.main.Dato d11 = new models.main.Dato( );
			d11.setId( 11L );
			d11.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d11.setSensor( s1 );
			d11.save( );

			models.main.Dato d12 = new models.main.Dato( );
			d12.setId( 12L );
			d12.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d12.setSensor( s1 );
			d12.save( );

			models.main.Dato d13 = new models.main.Dato( );
			d13.setId( 13L );
			d13.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d13.setSensor( s2 );
			d13.save( );

			models.main.Dato d14 = new models.main.Dato( );
			d14.setId( 14L );
			d14.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d14.setSensor( s2 );
			d14.save( );

			models.main.Dato d15 = new models.main.Dato( );
			d15.setId( 15L );
			d15.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d15.setSensor( s2 );
			d15.save( );

			models.main.Dato d16 = new models.main.Dato( );
			d16.setId( 16L );
			d16.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d16.setSensor( s2 );
			d16.save( );

			models.main.Dato d17 = new models.main.Dato( );
			d17.setId( 17L );
			d17.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d17.setSensor( s2 );
			d17.save( );

			models.main.Dato d18 = new models.main.Dato( );
			d18.setId( 18L );
			d18.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d18.setSensor( s2 );
			d18.save( );

			models.main.Dato d19 = new models.main.Dato( );
			d19.setId( 19L );
			d19.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d19.setSensor( s2 );
			d19.save( );

			models.main.Dato d20 = new models.main.Dato( );
			d20.setId( 20L );
			d20.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d20.setSensor( s2 );
			d20.save( );

			models.main.Dato d21 = new models.main.Dato( );
			d21.setId( 21L );
			d21.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d21.setSensor( s3 );
			d21.save( );

			models.main.Dato d22 = new models.main.Dato( );
			d22.setId( 22L );
			d22.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d22.setSensor( s3 );
			d22.save( );

			models.main.Dato d23 = new models.main.Dato( );
			d23.setId( 23L );
			d23.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d23.setSensor( s3 );
			d23.save( );

			models.main.Dato d24 = new models.main.Dato( );
			d24.setId( 24L );
			d24.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d24.setSensor( s3 );
			d24.save( );

			models.main.Dato d25 = new models.main.Dato( );
			d25.setId( 25L );
			d25.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d25.setSensor( s3 );
			d25.save( );

			models.main.Dato d26 = new models.main.Dato( );
			d26.setId( 26L );
			d26.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d26.setSensor( s3 );
			d26.save( );

			models.main.Dato d27 = new models.main.Dato( );
			d27.setId( 27L );
			d27.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d27.setSensor( s3 );
			d27.save( );

			models.main.Dato d28 = new models.main.Dato( );
			d28.setId( 28L );
			d28.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d28.setSensor( s3 );
			d28.save( );

			models.main.Dato d29 = new models.main.Dato( );
			d29.setId( 29L );
			d29.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d29.setSensor( s3 );
			d29.save( );

			models.main.Dato d30 = new models.main.Dato( );
			d30.setId( 30L );
			d30.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d30.setSensor( s3 );
			d30.save( );

			models.main.Dato d31 = new models.main.Dato( );
			d31.setId( 31L );
			d31.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d31.setSensor( s4 );
			d31.save( );

			models.main.Dato d32 = new models.main.Dato( );
			d32.setId( 32L );
			d32.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d32.setSensor( s4 );
			d32.save( );

			models.main.Dato d33 = new models.main.Dato( );
			d33.setId( 33L );
			d33.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d33.setSensor( s4 );
			d33.save( );

			models.main.Dato d34 = new models.main.Dato( );
			d34.setId( 34L );
			d34.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d34.setSensor( s4 );
			d34.save( );

			models.main.Dato d35 = new models.main.Dato( );
			d35.setId( 35L );
			d35.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d35.setSensor( s4 );
			d35.save( );

			models.main.Dato d36 = new models.main.Dato( );
			d36.setId( 36L );
			d36.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d36.setSensor( s4 );
			d36.save( );

			models.main.Dato d37 = new models.main.Dato( );
			d37.setId( 37L );
			d37.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d37.setSensor( s4 );
			d37.save( );

			models.main.Dato d38 = new models.main.Dato( );
			d38.setId( 38L );
			d38.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d38.setSensor( s4 );
			d38.save( );

			models.main.Dato d39 = new models.main.Dato( );
			d39.setId( 39L );
			d39.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d39.setSensor( s4 );
			d39.save( );

			models.main.Dato d40 = new models.main.Dato( );
			d40.setId( 40L );
			d40.setTimeStamp( java.util.Calendar.getInstance( ).getTime( ) );
			d40.setSensor( s4 );
			d40.save( );

		}

		if( Alerta.find.all( ).isEmpty( ) )
		{
			Area ar1 = new Area( );
			ar1.setId( 1L );
			Area ar2 = new Area( );
			ar2.setId( 2L );
			Area ar3 = new Area( );
			ar3.setId( 3L );

			Alerta a1 = new Alerta( );
			a1.setId( 1L );
			a1.setTipo( 1 );
			a1.setArea( ar1 );
			a1.save( );

			Alerta a2 = new Alerta( );
			a2.setId( 2L );
			a2.setTipo( 2 );
			a2.setArea( ar2 );
			a2.save( );

			Alerta a3 = new Alerta( );
			a3.setId( 3L );
			a3.setTipo( 3 );
			a3.setArea( ar3 );
			a3.save( );
		}

		if( Actuador.find.all( ).isEmpty( ) )
		{
			Area ar1 = new Area( );
			ar1.setId( 1L );
			Area ar2 = new Area( );
			ar2.setId( 2L );
			Area ar3 = new Area( );
			ar3.setId( 3L );

			Actuador a1 = new Actuador( );
			a1.setId( 1L );
			a1.setArea( ar1 );
			a1.save( );

			Actuador a2 = new Actuador( );
			a2.setId( 2L );
			a2.setArea( ar2 );
			a2.save( );

			Actuador a3 = new Actuador( );
			a3.setId( 3L );
			a3.setArea( ar3 );
			a3.save( );
		}
	}

	@Override
	public F.Promise<Result> onHandlerNotFound( Http.RequestHeader request )
	{
		return F.Promise.promise( ( ) -> Results.notFound( "No se encontró la ruta " + request.path( ) ) );
	}

	@Override
	public F.Promise<Result> onBadRequest( Http.RequestHeader request, String error )
	{
		return F.Promise.promise( ( ) -> Results.badRequest( error ) );
	}

	@Override
	public F.Promise<Result> onError( Http.RequestHeader request, Throwable t )
	{
		t.printStackTrace( );
		return F.Promise.promise( ( ) ->
								  {
									  String message = t.getMessage( );
									  return Results.internalServerError( message != null ? message : "" );
								  } );
	}
}
