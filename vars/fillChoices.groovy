def call(String filepath){
   /* List<String> readFileInList(String filePath) {
        File file = new File(filePath)
        def lines = file.readLines()
        return lines
    }*/
    def parameters = []
    /*List<String> lines = []
    File file = new File(filepath)
    lines = file.readLines()
    return lines*/
    new File(filepath).eachLine { line ->
        parameters << "line"
    }
    return parameters
    /*parameters.each {
        println it
    }*/
}