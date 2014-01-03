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

    public static class STUDENT
    {
        public static final String PICTURE_URI = ROOT + "pictures/";
        public static final String STUDENT = "student";
        public static final String ID = "id";
        public static final String LOGIN = "login";
        public static final String PASSWORD = "password";
        public static final String NAME = "name";
        public static final String SURNAME= "surname";
        public static final String PICTURE = "picture";
        public static final String TOKEN = "token";
    }


    public static class UVS
    {
        public static final String URI = ROOT + "liste_uv.php";
        public static final String URI_ONE = ROOT + "search.php";
        public static final String TOKEN = "token";
        public static final String UVS = "uvs";

        public static final String ID = "id";
        public static final String LABEL = "label";
        public static final String ID_DESCRIPTION = "id_description";
        public static final String ID_CATEGORY = "id_category";
    }

    public static class CATEGORY
    {
        public static final String CATEGORY = "category";

        public static final String ID = "id";
        public static final String LABEL = "label";
    }

    public static class DESCRIPTION
    {
        public static final String URI = ROOT + "details.php";
        public static final String TOKEN = "token";
        public static final String DESCRIPTION = "description";

        public static final String ID = "id";
        public static final String CURRICULA = "curricula";
        public static final String OBJECTIVES = "objectives";
        public static final String TYPE_UV = "type_uv";
        public static final String CREDITS = "credits";
        public static final String AVAILABALITY = "availability";
        public static final String LECTURES = "lectures";
        public static final String TUTORIALS = "tutorials";
        public static final String PRACTICALS = "practicals";
        public static final String PERSONNAL = "personnal";
        public static final String AVG_MARK = "avg_mark";
    }

    public static class COMMENT
    {
        public static final String URI = ROOT + "liste_comment.php";
        public static final String URI2 = ROOT + "add_comment.php";
        public static final String TOKEN = "token";
        public static final String COMMENT = "comment";
        public static final String COMMENTS = "comments";

        public static final String ID = "id";
        public static final String TEXT = "text";
        public static final String ID_STUDENT = "id_student";
        public static final String ID_UV = "id_uv";
        public static final String MARK = "mark";
    }
}
