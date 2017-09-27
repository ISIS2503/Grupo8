
#include <string.h>
#include <Adafruit_Sensor.h>
#include <Adafruit_TSL2561_U.h>
#include <DHT11.h>

// ---------------------------------CONSTANTES---------------------------------
// Id del microcontrolador
const int id = 1;
// Id del nivel
const int idNivel = 1;
// Id del area
const int idArea = 1;
// Selecciona el pin de entrada analoga a leer la Temperatura.
const int temperaturaPin = 3;
// Selecciona el pin de entrada analoga a leer el CO.
const int COPin = 2;
// Selecciona el pin de entrada analoga a leer la Ilumunacion.
const int iluminacionPin = -1; // 5 y 4
// Selecciona el pin de entrada analoga a leer el Sonido.
const int sonidoPin = 0;
// Link con el sensor de temperatura
const DHT11 dht11(temperaturaPin);
// Link con el sensor de iluminación
const Adafruit_TSL2561_Unified tsl = Adafruit_TSL2561_Unified(TSL2561_ADDR_FLOAT, 12345);

const String separadorAtributos = ":::";
const String separadorTipos = ";;;";

const String temperaturaTipo = "C";
const String COTipo = "ppm";
const String iluminacionTipo = "Lux";
const String sonidoTipo = "dB";

// ---------------------------------VARIABLES---------------------------------
float temperatura = 0.0;
int CO = 0;
int iluminacion = 0;
int sonido = 0;

int actualChar = 0;
String tempString = "";
String paquete = "";

int enviaRuidoEIluminacion = 0;

// Preparacion del proceso
void setup() 
{
    // Abre puerto serial y lo configura a 9600 BPS
    Serial.begin( 9600 );
    // Configura el sensor de iluminación
    setupIluminacion();
}

// Configura el sensor de iluminación
void setupIluminacion()
{
    if(!tsl.begin())
    {
        Serial.println("Error con TSL");
    }

    tsl.enableAutoRange(true);
    tsl.setIntegrationTime(TSL2561_INTEGRATIONTIME_13MS);
}

// Ejecuta el procesamiento del microcontrolador
void loop() 
{
    tempString = idNivel + separadorAtributos + idArea + separadorAtributos + id + separadorAtributos + "ID" + separadorTipos;
    // Lee el nivel de la temperatura 
    readTemperatura();
    // Lee los niveles de CO
    readCO();

    if( enviaRuidoEIluminacion )
    {
        // Lee los niveles de la iluminación
        readIluminacion();
        // Lee los niveles de Sonido
        readSonido();
    }
    else
    {
        sendNull();
        sendNull();
    }

    // Envia la información por el puerto Serial
    Serial.println(tempString);
    tempString = "";

    // Espera 1 segundo para repetir el procedimiento
    enviaRuidoEIluminacion = (enviaRuidoEIluminacion + 1) % 2;
    delay(60000);
}

// Lee los nivels de temperatura
void readTemperatura()
{
    float humedad;
    int err = dht11.read(humedad, temperatura);
    if( err == 0)    // Si devuelve 0 es que ha leido bien
    { 
      tempString += String(temperatura);
      tempString += separadorAtributos;
      tempString += temperaturaTipo;
      tempString += separadorTipos;
    }
    else
    {
      Serial.print("Error ");
      Serial.print(err);
      Serial.println("");
    }
}

void readCO()
{
    CO = analogRead(COPin);

    tempString += String(CO);
    tempString += separadorAtributos;
    tempString += COTipo;
    tempString += separadorTipos;
}

void readIluminacion()
{
    sensors_event_t event;
    tsl.getEvent(&event);

    if(event.light)
    {
        tempString += String(event.light);
        tempString += separadorAtributos;
        tempString += iluminacionTipo;
        tempString += separadorTipos;
    }
}

void readSonido()
{
    sonido = analogRead(sonidoPin);

    tempString += String(sonido);
    tempString += separadorAtributos;
    tempString += sonidoTipo;
    tempString += separadorTipos;
}

void sendNull()
{
    tempString += "NULL";
    tempString += separadorTipos;
}


