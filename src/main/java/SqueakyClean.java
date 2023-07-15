class SqueakyClean {
    String stringIdentifier;

    public SqueakyClean(String identifier) {
        this.stringIdentifier = identifier;
    }

    public String resolve() {
        return this.stringIdentifier;
    }

    public SqueakyClean replaceSpacesToUnderscores() {
        this.stringIdentifier = this.stringIdentifier.replace(" ", "_");
        return this;
    }

    public SqueakyClean replaceControlCharactersToCTRL() {
        this.stringIdentifier = this.stringIdentifier.replaceAll("\\p{Cntrl}", "CTRL");
        return this;
    }

    public SqueakyClean removeNoPrintableCharacters() {
        this.stringIdentifier = this.stringIdentifier.replaceAll("[\\p{C}\\p{So}]+", "");
        return this;
    }

    public SqueakyClean removeGreekLowerCaseLetters() {
        this.stringIdentifier = this.stringIdentifier.replaceAll("[α-ω]", "");
        return this;
    }

    public SqueakyClean removeDigits() {
        this.stringIdentifier = this.stringIdentifier.replaceAll("\\d", "");
        return this;
    }

    public SqueakyClean convertKebabCaseToCamelCase() {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = false;
        for (char c : this.stringIdentifier.toCharArray()) {
            if (c == '-') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                camelCase.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                camelCase.append(c);
            }
        }
        this.stringIdentifier = camelCase.toString();
        return this;
    }


    static String clean(String identifier) {
        SqueakyClean squeakyClean = new SqueakyClean(identifier);

        return squeakyClean
                .replaceSpacesToUnderscores()
                .replaceControlCharactersToCTRL()
                .removeNoPrintableCharacters()
                .removeGreekLowerCaseLetters()
                .removeDigits()
                .convertKebabCaseToCamelCase()
                .resolve();
    }
}