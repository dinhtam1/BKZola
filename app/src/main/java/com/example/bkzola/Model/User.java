package com.example.bkzola.Model;

public class User {
        private String id;
        private String usename;
        private String imageURL;

        public User(String id,String usename,String imageURL){
            this.id = id;
            this.usename = usename;
            this.imageURL = imageURL;
        }
        public User(){

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsename() {
            return usename;
        }

        public void setUsename(String usename) {
            this.usename = usename;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }
}
