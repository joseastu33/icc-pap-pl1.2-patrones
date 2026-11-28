# Casos de prueba — SnakeLab

| ID | Operación / entrada | Resultado esperado | Aspecto validado | Evidencia |
|---|---|---|---|---|
| CA01 | Abrir SnakeLab | Se visualiza el tablero y la partida inicia en Preparado, sin movimiento. | State | <img src="./CA01_abrir_snakelab.png" width="250"> |
| CA02 | Iniciar y cambiar dirección con flechas | La serpiente se desplaza; se impide el giro directo de 180°. | Movimiento | <img src="./CA02_movimiento_serpiente.png" width="250"> |
| CA03 | Pausar y reanudar | La posición y las duraciones de efectos no cambian durante la pausa. | State | <img src="./CA03_pausar_reanudar.png" width="250"> |
| CA04 | Provocar colisión con borde | La partida pasa a Terminado y detiene el desplazamiento. | State / colisión | <img src="./CA04_colision_borde.png" width="250"> |
| CA05 | Provocar colisión con el cuerpo | La partida finaliza correctamente. | Colisión | <img src="./CA05_colision_cuerpo.png" width="250"> |
| CA06 | Consumir comida normal | La longitud aumenta y se actualiza la puntuación definida. | Mecánica | <img src="./CA06_comida_normal.png" width="250"> |
| CA07 | Ubicar comida en coordenadas libres | La comida aparece exactamente en X e Y. | Panel manual | <img src="./CA07_ubicar_comida.png" width="250"> |
| CA08 | Ingresar texto no numérico o coordenadas fuera de límites | Se rechaza la operación sin modificar la comida existente. | Validación | <img src="./CA08_validacion_coordenadas.png" width="250"> |
| CA09 | Intentar ubicar comida sobre la serpiente | Se rechaza la casilla ocupada y se conserva el tablero. | Validación | <img src="./CA09_comida_sobre_serpiente.png" width="250"> |
| CA10 | Presionar Generar aleatoria | La comida se ubica dentro de los límites y fuera del cuerpo. | Generación | <img src="./CA10_comida_aleatoria.png" width="250"> |
| CA11 | Solicitar comida sin casillas disponibles | Se informa que el tablero está completo y la partida se resuelve coherentemente. | Caso límite | <img src="./CA11_tablero_completo.png" width="250"> |
| CA12 | Consumir alimento de puntuación doble | Se activa el decorador y se observa la modificación de los puntos durante su vigencia. | Decorator | <img src="./CA12_puntuacion_doble.png" width="250"> |
| CA13 | Consumir alimento de velocidad | Se aplica el decorador y cambia el intervalo de desplazamiento. | Decorator | <img src="./CA13_efecto_velocidad.png" width="250"> |
| CA14 | Mantener ambos efectos activos | Se comprueba la composición de los dos decoradores y su finalización. | Decorator | <img src="./CA14_efectos_combinados.png" width="250"> |
| CA15 | Reiniciar una partida | Se restablecen cuerpo, puntuación, velocidad, comida y efectos. | State / reinicio | <img src="./CA15_reiniciar_partida.png" width="250"> |
| CA16 | Ejecutar caso del tercer patrón | Una operación real demuestra la intervención de sus participantes. | Tercer patrón | <img src="./CA16_tercer_patron.png" width="250"> |
| CA17 | Ejecutar `java -jar SnakeLab.jar` | El juego abre desde terminal, sin iniciar el IDE, con la versión documentada. | Distribución | <img src="./CA17_ejecucion_jar.png" width="250"> |
