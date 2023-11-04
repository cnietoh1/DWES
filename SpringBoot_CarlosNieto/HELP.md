# Demo SpringBoot 
## Carlos Nieto

### Contenidos
* Estructura MVC
    * @Valid
  
    * bindingResult.hasErrors()
  
    * bindingResult.rejectValue()
  
    * Anotaciones de validación de campos:
      * @Min(1) para valores numéricos
      * @NotNull, @NotEmpty, @NotBlank para strings
      * @Past `para fechas`

    * Mensajes de validación:
      * application.properties
          * spring.messages.basename=mensajes

    * Lombok:
        * @NoArgsConstructor
        * @Slf4j

    * Cambiar idioma a través de la URL indicando:
        * ?lang=es_ES para español
        * ?lang=en_US para inglés