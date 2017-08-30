#include <string.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_TSL2561_U.h>
#include <DHT11.h>

// ---------------------------------CONSTANTES---------------------------------
// Selecciona el pin de entrada analoga a leer la Temperatura.
int temperaturaPin = 3;
// Selecciona el pin de entrada analoga a leer el CO2.
int co2Pin = 2;
// Selecciona el pin de entrada analoga a leer la Ilumunacion.
int iluminacionPin = -1; // 5 y 4
// Selecciona el pin de entrada analoga a leer el Sonido.
int sonidoPin = 1;

// Link con el sensor de temperatura
DHT11 dht11(temperaturaPin);

// Link con el sensor de temperatura
Adafruit_TSL2561_Unified tsl = Adafruit_TSL2561_Unified(TSL2561_ADDR_FLOAT, 12345);

// ---------------------------------VARIABLES---------------------------------
float temperatura = 0.0;
int co2 = 0;
int iluminacion = 0;
int sonido = 0;

String temperaturaTipo = "C";
String co2Tipo = "ppm";
String iluminacionTipo = "Lux";
String sonidoTipo = "dB";

int actualChar = 0;
String tempString = "";
String paquete = "";

// Preparacion del proceso
void setup() 
{
    // Abre puerto serial y lo configura a 9600 bps
    Serial.begin( 9600 );

    setupIluminacion();
 }
 
void setupIluminacion()
{
    // Connect SCL to analog 5
    // Connect SDA to analog 4
    // Connect VDD to 3.3V DC
    // Connect GROUND to common ground

    if(!tsl.begin())
    {
        Serial.println("Error con TSL");
    }

    tsl.enableAutoRange(true);
    tsl.setIntegrationTime(TSL2561_INTEGRATIONTIME_13MS);
}

// Ejecuta el procesamiento del sensor
void loop() 
{
    readTemperatura();
    readCO2();
    readIluminacion();
    readSonido();
    // Espera 1 segundo para repetir el procedimiento
    delay(1000);

    Serial.println(tempString);
    tempString = "";
}

void readTemperatura()
{
    float humedad;
    int err = dht11.read(humedad, temperatura);
    if( err == 0)    // Si devuelve 0 es que ha leido bien
    { 
      tempString += String(temperatura);
      tempString += ":::";
      tempString += temperaturaTipo;
      tempString += ";;;";
    }
    else
    {
      Serial.print("Error ");
      Serial.print(err);
      Serial.println("");
    }
}

void readCO2()
{
    co2 = analogRead(co2Pin);

    tempString += String(co2);
    tempString += ":::";
    tempString += co2Tipo;
    tempString += ";;;";
}

void readIluminacion()
{
    sensors_event_t event;
    tsl.getEvent(&event);

    if(event.light)
    {
        tempString += String(event.light);
        tempString += ":::";
        tempString += iluminacionTipo;
        tempString += ";;;";
    }
}

void readSonido()
{
    // sonido = analogRead(sonidoPin);
    sonido = 40;//TODO

    tempString += String(sonido);
    tempString += ":::";
    tempString += sonidoTipo;
    tempString += ";;;";
}

