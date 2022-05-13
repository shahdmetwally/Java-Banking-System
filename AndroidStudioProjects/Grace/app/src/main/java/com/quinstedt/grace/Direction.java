package com.quinstedt.grace;

public enum Direction {

    FORWARD{
        @Override
        public String toString() {
            return "forward";
        }
    }, BACKWARD{
        @Override
        public String toString() {
            return "backward";
        }

    },LEFT{
        @Override
        public String toString() {
            return "left";
        }

    },RIGHT{
        @Override
        public String toString() {
            return "right";
        }

    },STOP{
        @Override
        public String toString() {
            return "stop";
        }

    }, UPLEFT{
        @Override
        public String toString() {
            return "up-left";
        }

    },UPRIGHT{
        @Override
        public String toString() {
            return "up-right";
        }

    },BACKLEFT{
        @Override
        public String toString() {
            return "back-left";
        }

    },BACKRIGHT{
        @Override
        public String toString() {
            return "back-right";
        }

    };
}
