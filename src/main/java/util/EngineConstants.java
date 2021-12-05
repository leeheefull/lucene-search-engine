package util;

public class EngineConstants {
    private EngineConstants() {
    }

    public static class Path {
        private Path() {
        }

        public static final String INDEX_PATH = "src/main/resources/index";
        public static final String POST_JSON_PATH = "src/main/resources/json/data.json";
    }

    public static class IndexField {
        private IndexField() {
        }

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String WRITER = "writer";
        public static final String CONTENT = "content";
    }

    public static class Type {
        private Type() {
        }

        public static final String WILDCARD = "*";
    }

    public static class Menu {
        private Menu() {
        }

        public static final int INDEX_MENU = 1;
        public static final int SEARCH_MENU = 2;
        public static final int END_SERVICE = 3;
        public static final String NUM_ERROR_MSG = "없는 메뉴입니다.";

    }

    public static class Log {
        private Log() {
        }

        public static final String ERROR_LOG_PATH = "src/main/resources/error.log";
        public static final String LINE_BREAK = "---------------------------------------------------------------------------------------------------------";
    }
}
