package by.school.service.generation;

public enum DocumentType {
    PDF("PDF"),
    CSV("CSV"),
    XLSX("XLSX");

    private String typeName;

    DocumentType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public DocumentType getByTypeName(String typeName) {
        DocumentType[] values = DocumentType.values();
        for (DocumentType value : values) {
            if (value.getTypeName().equals(typeName))
                return value;
        }
        return null;
    }
}
