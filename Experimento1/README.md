# Descripción de Conexión

## Sensor de Temperatura: 
	Modelo: DH11  
	Pin: D3  
	V: 5V  

## Sensor de Iluminación: 
	Modelo: TSL2561  
	Pin: SCL = A5  
	     SDA = A4  
	V: 5V  

## Sensor de CO: 
	Modelo: MQ-7 (Con circuito)  
	Pin: A2 (Analog Output)  
	     D8 (Digital Outpt)  
	V: 5V  

## Sensor de Sonido: ---
	Modelo: LM393
	Pin: A0
	V: 5V

# Protocolo de Comunicación:

| Descripción                 | Valor           | Ejemplo  |
| --------------------------- |:---------------:| :--------:|
| Separador de atributos      | :::             | 27:::C   |
| Separador de tipos          | ;;;             |  \<Temperatura\>;;;\<Iluminación\>  |
| Valor Nulo                  | NULL            |    \<Ruido\>;;;NULL |

El orden de los tipos en el protocolo está dado por:
1. Temperatura
2. Iluminación
3. Gases (Monóxido de Carbono)
4. Ruido
