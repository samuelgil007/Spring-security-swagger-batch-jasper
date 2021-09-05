package com.konectaBack.konectaBack.Constants;

public final class ControllerConstants {

    private ControllerConstants(){}

    //public static final String FRONT_END = "https://samuelgil007.github.io/KonectaTestFront";
    public static final String FRONT_END = "*";
    public static final String PACIENTE = "/paciente";

    public static final String USUARIO = "/usuario";

    public static final String MEDICO = "/medico";
    public static final String MEDICO_ID = MEDICO+"/{id}";
    public static final String MEDICO_ID_DESACTIVATE = MEDICO_ID+"/desactivate";
    public static final String MEDICO_ID_ACTIVATE = MEDICO_ID+"/activate";

    public static final String LOGIN = "/login";

    public static final String CITA = "/cita";
    public static final String CITA_ID = CITA+"/{id}";
    public static final String CITA_MEDICO_ID = CITA+"/medico/{id}";
    public static final String CITA_PACIENTE_ID = CITA+"/paciente/{id}";

}
