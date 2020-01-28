def call(String filepath){
    List<String> readFileInList(String filePath) {
        File file = new File(filePath)
        def lines = file.readLines()
        return lines
    }
}