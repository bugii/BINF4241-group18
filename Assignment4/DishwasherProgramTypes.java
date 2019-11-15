public enum DishwasherProgramTypes {
    Glasses, Plates, Pans, Mixed;

    int getProgramDuration() {
        if (this == Glasses) {
            return 1;
        }

        if (this == Plates) {
            return 2;
        }

        if (this == Pans) {
            return 3;
        }

        if (this == Mixed) {
            return 4;
        }

        else return 0;
    }
}