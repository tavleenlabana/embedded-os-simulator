class Process {
    int pId;
    int burstTime;
    int remainingTime;
    int memory;
    String state;

    Process(int pId, int burstTime, int memory) {
        this.pId = pId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.memory = memory;
        this.state = "READY";
    }
}


