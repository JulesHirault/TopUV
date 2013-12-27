package com.if26.topuv.constants;

/**
 * Created by Flo on 12/12/2013.
 */
public class WSConstants {
    public static final String ROOT = "http://pinatflo.esy.es/";

    public static class LOGIN
    {
        public static final String URI = ROOT + "login.php";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String TOKEN = "token";
    }

    public static class UVS
    {
        public static final String URI = ROOT + "liste_uv.php";
        public static final String TOKEN = "token";
        public static final String UVS = "uvs";

        public static final String ID = "id";
        public static final String LABEL = "label";
        public static final String ID_DESCRIPTION = "id_description";
        public static final String AVG_MARK = "avg_mark";
        public static final String ID_CATEGORY = "id_category";

    }

    public static class CATEGORY
    {
        public static final String CATEGORY = "category";

        public static final String ID = "id";
        public static final String LABEL = "label";

    }
}
