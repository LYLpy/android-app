package net.getlearn.getlearn_android.Event;

import java.util.List;

/*
* 传递
* */
public class CommonIntengEvent {

    private List<SelectCoursebean> select_course;

    public static class SelectCoursebean {
        private int subject_id;
        private String subject;
        private Versionbean version;

        public int getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(int subject_id) {
            this.subject_id = subject_id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public Versionbean getVersion() {
            return version;
        }

        public void setVersion(Versionbean version) {
            this.version = version;
        }

        public static class Versionbean {
            private int id;
            private String version;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }

    }
}
