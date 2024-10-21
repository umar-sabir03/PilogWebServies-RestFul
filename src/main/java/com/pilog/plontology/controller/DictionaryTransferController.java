//package com.pilog.plontology.beans.controller;
//
//import com.pilog.plontology.beans.OntologyClassDataRow;
//import com.pilog.plontology.beans.OntologyDataRow;
//import com.pilog.plontology.repository.pprm.PreferenceOntology;
//import com.pilog.plontology.service.impl.SessionMapBuilder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//@RestController
//@RequestMapping("/dictionary")
//public class DictionaryTransferController {
//
//    private static final ConcurrentHashMap<String, RunGetTemplates> tasks = new ConcurrentHashMap<>();
//    private static final ConcurrentHashMap<String, RunGetVersions> requests = new ConcurrentHashMap<>();
//    private static final Timer taskManagementThread;
//    private static final Timer requestManagementThread;
//    private static final Boolean IS_DAEMON = true;
//
//    static {
//        taskManagementThread = new Timer("Task Management Timer", IS_DAEMON);
//        taskManagementThread.scheduleAtFixedRate(new RequestManagement(), 1000, (24 * 60 * 60 * 1000));
//        requestManagementThread = new Timer("Request Management Timer", IS_DAEMON);
//        requestManagementThread.scheduleAtFixedRate(new TaskManagement(), 1000, (24 * 60 * 60 * 1000));
//    }
//
//    // POST method for generating dictionary versions
//    @PostMapping("/generateDictionaryVersions")
//    public String generateDictionaryVersions(
//            @RequestParam String sourceCode,
//            @RequestParam String dataSourceName,
//            @RequestParam String orgID,
//            @RequestParam String ssUsername) {
//
//        UUID processID = UUID.randomUUID();
//        SessionMapBuilder sessionHashMap = new SessionMapBuilder(ssUsername, dataSourceName);
//        sessionHashMap.addKV("orgID", orgID);
//        RunGetVersions thread = new RunGetVersions(sessionHashMap.getSessionHashMap());
//        requests.put(processID.toString(), thread);
//        Thread processThread = new Thread(thread);
//        processThread.start();
//        return processID.toString(); // Return the process ID
//    }
//
//    // GET method to check if the process is completed
//    @GetMapping("/isGenerateDictionaryVersionsDone/{processID}")
//    public boolean isGenerateDictionaryVersionsDone(@PathVariable String processID) throws Exception {
//        if (requests.containsKey(processID)) {
//            return requests.get(processID).getIsDone();
//        } else {
//            throw new Exception("Task " + processID + " is not yet registered or has been removed.");
//        }
//    }
//
//    // GET method to retrieve dictionary versions once the process is complete
//    @GetMapping("/getDictionaryVersions/{processID}")
//    public String[] getDictionaryVersions(@PathVariable String processID) throws Exception {
//        if (!requests.containsKey(processID)) {
//            throw new Exception("Request " + processID + " is not yet registered or has been removed.");
//        }
//        if (requests.get(processID).getIsDone()) {
//            String[] dictVersions = requests.get(processID).getDictVersions();
//            requests.remove(processID);  // Cleanup after completion
//            return dictVersions;
//        } else {
//            throw new Exception("Request " + processID + " is not yet completed.");
//        }
//    }
//
//    // POST method to generate templates
//    @PostMapping("/generateTemplates")
//    public String generateTemplates(
//            @RequestParam String sourceCode,
//            @RequestParam String orgID,
//            @RequestParam String[] dataRequirementList,
//            @RequestParam String dataSourceName,
//            @RequestParam String dictionaryLocales,
//            @RequestParam String uscVersion,
//            @RequestParam String ssUsername) throws Exception {
//
//        UUID processID = UUID.randomUUID();
//        SessionMapBuilder builder = new SessionMapBuilder(ssUsername, dataSourceName);
//        builder.addKV("sourceCode", sourceCode);
//        builder.addKV("orgID", orgID);
//        builder.addKV("dictionaryLocales", dictionaryLocales);
//        builder.addKV("uscVersion", uscVersion);
//        HashMap<String, Object> sessionHashMap = builder.getSessionHashMap();
//        RunGetTemplates thread = new RunGetTemplates(sessionHashMap, dataRequirementList);
//        tasks.put(processID.toString(), thread);
//        Thread processThread = new Thread(thread);
//        processThread.start();
//        return processID.toString();  // Return the process ID
//    }
//
//    // GET method to check if the template generation process is completed
//    @GetMapping("/isGenerateTemplatesDone/{processID}")
//    public boolean isGenerateTemplatesDone(@PathVariable String processID) throws Exception {
//        if (tasks.containsKey(processID)) {
//            return tasks.get(processID).getIsDone();
//        } else {
//            throw new Exception("Task " + processID + " is not yet registered or has been removed.");
//        }
//    }
//
//    // GET method to retrieve generated templates once the process is complete
//    @GetMapping("/getTemplates/{processID}")
//    public OntologyClassDataRow[] getTemplates(@PathVariable String processID) throws Exception {
//        if (!tasks.containsKey(processID)) {
//            throw new Exception("Task " + processID + " is not yet registered or has been removed.");
//        }
//        if (tasks.get(processID).getIsDone()) {
//            OntologyClassDataRow[] template = tasks.get(processID).getTemplates();
//            tasks.remove(processID);  // Cleanup after completion
//            return template;
//        } else {
//            throw new Exception("Task " + processID + " is not yet completed.");
//        }
//    }
//
//    // GET method to retrieve preference ontology
//    @GetMapping("/getPreferenceOntology")
//    public OntologyDataRow[] getPreferenceOntology(
//            @RequestParam String orgID,
//            @RequestParam String instance,
//            @RequestParam String ssUsername) throws Exception {
//        return getPrefernceOntologyWithLanguageID(orgID, "%", instance, ssUsername);
//    }
//
//    @GetMapping("/getPrefernceOntologyWithLanguageID")
//    public OntologyDataRow[] getPrefernceOntologyWithLanguageID(
//            @RequestParam String orgID,
//            @RequestParam String languageID,
//            @RequestParam String instance,
//            @RequestParam String ssUsername) throws Exception {
//
//        SessionMapBuilder sessionHashMap = new SessionMapBuilder(ssUsername, instance);
//        sessionHashMap.addKV("orgID", orgID);
//        sessionHashMap.addKV("languageID", languageID);
//        PreferenceOntology preferenceOntology = new PreferenceOntology();
//        return preferenceOntology.getDataSet(sessionHashMap.getSessionHashMap());
//    }
//
//    static class TaskManagement extends TimerTask {
//        @Override
//        public void run() {
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.DAY_OF_MONTH, -1);
//            ArrayList<String> keys = new ArrayList<>();
//            for (String string : tasks.keySet()) {
//                if (tasks.get(string).getTaskRegistrationTime() < (calendar.getTimeInMillis())) {
//                    keys.add(string);
//                }
//            }
//            for (String string : keys) {
//                tasks.remove(string);
//            }
//        }
//    }
//
//    static class RequestManagement extends TimerTask {
//        @Override
//        public void run() {
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.DAY_OF_MONTH, -1);
//            ArrayList<String> keys = new ArrayList<>();
//            for (String string : requests.keySet()) {
//                if (requests.get(string).getTaskRegistrationTime() < (calendar.getTimeInMillis())) {
//                    keys.add(string);
//                }
//            }
//            for (String string : keys) {
//                requests.remove(string);
//            }
//        }
//    }
//}
//
//class RunGetTemplates implements Runnable
//{
//
//    private HashMap<String, Object> sessionHashMap;
//    private List<String> dataRequirementList;
//    private List<OntologyClassDataRow> template;
//    private Boolean isDone;
//    private Long taskRegistrationTime;
//    private List<GenerateTemplate> threads;
//
//    public RunGetTemplates(HashMap<String, Object> sessionHashMap,
//                           String[] dataRequirementList)
//    {
//        this.taskRegistrationTime = System.currentTimeMillis();
//        this.sessionHashMap = sessionHashMap;
//        this.dataRequirementList = Arrays.asList(dataRequirementList);
//        threads = new ArrayList<GenerateTemplate>();
//        this.isDone = false;
//        this.template = new ArrayList<OntologyClassDataRow>();
//    }
//
//    @Override
//    public void run()
//    {
//        System.out.println("RunGetTemplates is Starting...");
//        this.isDone = false;
//        if (dataRequirementList.isEmpty())
//        {
//            //someone is just waisting my time.
//            this.isDone = true;
//            return;
//        }
//        /* This threadCount variable may only be values of 5,10,25,50.
//         * Each Thread will manage its own connection, show make sure the connection pool can
//         * handle multiple connections that each create this amount of connections.
//         */
//        int threadsCount = 25;
//        if (dataRequirementList.size() < threadsCount)
//        {
//            //Lets just make threads equal the to amount of items that needs to be processed.
//            threadsCount = dataRequirementList.size();
//        }
//        int batchSize = dataRequirementList.size() / threadsCount;
//        System.out.println("DataRequirementList Size: " + dataRequirementList.size());
//        System.out.println("DataRequirementList Batch Size: " + batchSize);
//        for (int i = 0; i < (threadsCount - 1); i++)
//        {
//            GenerateTemplate generate = new GenerateTemplate(sessionHashMap, dataRequirementList.subList(batchSize * i, (batchSize * i) + batchSize), 1 + i);
//            threads.add(generate);
//            Thread myChild = new Thread(generate, "Generate Template Thread " + i);
//            generate.setExecutionThread(myChild);
//            myChild.setDaemon(true);
//            myChild.start();
//        }
//        {
//            GenerateTemplate generate = new GenerateTemplate(sessionHashMap, dataRequirementList.subList(batchSize * (threadsCount - 1), dataRequirementList.size()), threadsCount);
//            threads.add(generate);
//            Thread myChild = new Thread(generate, "Generate Template Thread " + threadsCount);
//            generate.setExecutionThread(myChild);
//            myChild.setDaemon(true);
//            myChild.start();
//        }
//        for (GenerateTemplate generatedTemplate : threads)
//        {
//            try
//            {
//                generatedTemplate.getExecutionThread().join();
//            }
//            catch (InterruptedException ex)
//            {
//                Logger.log(Logger.ERRORDEBUG, "Thread sleep error (2).");
//                Logger.log(Logger.ERRORDEBUG, "Caused by: " + ex.getMessage());
//                ex.printStackTrace();
//            }
//            template.addAll(generatedTemplate.getTemplate());
//        }
//        this.isDone = true;
//        System.out.println("Batch template processing completed: " + this.isDone);
//    }
//
//    /**
//     * @return the templates
//     */
//    public OntologyClassDataRow[] getTemplates()
//    {
//        return template.toArray(new OntologyClassDataRow[template.size()]);
//    }
//
//    /**
//     * @return the isDone
//     */
//    public Boolean getIsDone()
//    {
//        return this.isDone;
//    }
//
//    /**
//     * @return the taskRegistrationTime
//     */
//    public Long getTaskRegistrationTime()
//    {
//        return this.taskRegistrationTime;
//    }
//
//    class GenerateTemplate implements Runnable
//    {
//
//        private HashMap<String, Object> sessionHashMap;
//        private List<String> dataRequirementList;
//        private OntologyClassDataRow[] template;
//        private Integer threadIndex;
//        private Thread executionThread;
//
//        private GenerateTemplate(HashMap<String, Object> sessionHashMap,
//                                 List<String> dataRequirementList,
//                                 Integer threadIndex)
//        {
//            this.sessionHashMap = sessionHashMap;
//            this.dataRequirementList = dataRequirementList;
//            this.threadIndex = threadIndex;
//        }
//
//        @Override
//        public void run()
//        {
//            Long startTime = System.currentTimeMillis();
//            System.out.println("Starting inner Thread " + threadIndex);
//            try
//            {
//                this.template = Templates.getTemplates(sessionHashMap, dataRequirementList.toArray(new String[dataRequirementList.size()]), threadIndex);
//            }
//            catch (Throwable ex)
//            {
//                Logger.log(Logger.ERRORDEBUG, "Failed to retrieve Templates.");
//                Logger.log(Logger.ERRORDEBUG, "Caused by: " + ex.getMessage());
//                ex.printStackTrace();
//            }
//            System.out.println("Excution Time: " + (System.currentTimeMillis() - startTime) + " ms");
//        }
//
//        /**
//         * @return the template
//         */
//        public List<OntologyClassDataRow> getTemplate()
//        {
//            if (template == null)
//            {
//                return new ArrayList<OntologyClassDataRow>(0);
//            }
//            return Arrays.asList(this.template);
//        }
//
//        /**
//         * @return the executionThread
//         */
//        public Thread getExecutionThread()
//        {
//            return executionThread;
//        }
//
//        /**
//         * @param executionThread the executionThread to set
//         */
//        public void setExecutionThread(Thread executionThread)
//        {
//            this.executionThread = executionThread;
//        }
//    }
//}
//
//class RunGetVersions implements Runnable
//{
//
//    private HashMap<String, Object> sessionHashMap;
//    private String[] dictVersions = null;
//    private Boolean isDone;
//    private Long taskRegistrationTime;
//
//    public RunGetVersions(HashMap<String, Object> sessionHashMap)
//    {
//        this.taskRegistrationTime = System.currentTimeMillis();
//        this.sessionHashMap = sessionHashMap;
//        this.isDone = false;
//    }
//
//    @Override
//    public void run()
//    {
//        this.isDone = false;
//
//        try
//        {
//            System.out.println("sessionHashMap: " + this.sessionHashMap);
//            this.dictVersions = DictionaryVersions.getDictionaryVersion(this.sessionHashMap);
//        }
//        catch (Throwable ex)
//        {
//            this.dictVersions = new String[3];
//            this.dictVersions[0] = "error";
//            this.dictVersions[1] = "An error has occured while trying to retrieve the downloadable data requirement no's from the Ontology Manager.";
//            this.dictVersions[2] = "(Error in DictionaryTransferService.war: Method getDictionaryVersions.\n" + ex.getMessage() + ")";
//            ex.printStackTrace();
//        }
//        this.isDone = true;
//    }
//
//    /**
//     * @return the isDone
//     */
//    public Boolean getIsDone()
//    {
//        return this.isDone;
//    }
//
//    /**
//     * @return the dictVersions
//     */
//    public String[] getDictVersions()
//    {
//        return this.dictVersions;
//    }
//
//    /**
//     * @return the taskRegistrationTime
//     */
//    public Long getTaskRegistrationTime()
//    {
//        return this.taskRegistrationTime;
//    }
//}