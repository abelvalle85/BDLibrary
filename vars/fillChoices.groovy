def call(Map f){
   /* List<String> readFileInList(String filePath) {
        File file = new File(filePath)
        def lines = file.readLines()
        return lines
    }*/
    //def StageServers = ['pajamas-all','pajamas-1','pajamas-3','pajamas-4']

    //def ProductionServers = ['tuxedo-all','tuxedo-1','tuxedo-2','tuxedo-3','tuxedo-4']
    def lines=[]
    File file=new File("${get_resource_dir()}/${f.ENV}Servers.txt")
    lines=file.readLines()
    return lines
    /*new File(filepath).eachLine { line ->
        parameters << line
    }
    return parameters*/
    /*parameters.each {
        println it
    }*/


}