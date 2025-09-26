# Operating Systems Project – Task Scheduling and Resource Management

## Overview

This project is an implementation of a simplified task scheduling and processor management system, written in Java. The goal is to simulate how an operating system manages resources such as cache, memory, and processor frequency when multiple tasks arrive with different requirements and deadlines.

The system models both **tasks** (with attributes like arrival time, deadline, value, and resource requirements) and **processors** (with cache, memory, and frequency capacities). It provides a framework to study scheduling strategies and decision-making under resource constraints.

---

## Key Components

- **Task Representation**  
  Each task is modeled with attributes:
  - Arrival time  
  - Deadline  
  - Value (or priority)  
  - Cache, memory, and frequency requirements  

- **Processor Representation**  
  Each processor is modeled with attributes:
  - Cache capacity  
  - Memory capacity  
  - Frequency  

- **Scheduling Logic**  
  - Determines whether a task can be assigned to an available processor.  
  - Considers deadlines, values, and resource constraints.  
  - Provides a simplified version of real OS scheduling algorithms.  

- **Task Manager**  
  Responsible for coordinating tasks and processors. It decides:
  - Which tasks can be scheduled immediately.  
  - Which tasks must wait or be rejected.  
  - How to maximize task value under limited resources.  

- **Supporting Utilities**  
  - Tuple-like data structures for passing task/processor state.  
  - CSV input/output generation for simulating workloads.  

---

## Learning Objectives

This project demonstrates core concepts of operating systems, including:

- Task scheduling and dispatching  
- Resource allocation (cache, memory, frequency)  
- Deadline-aware scheduling  
- Trade-offs between throughput and value-based task prioritization  
- Modeling scheduling decisions with real-world constraints  

---

## Purpose

The project is designed as a teaching and experimentation tool. It helps understand how:

- Operating systems must balance limited hardware resources.  
- Different scheduling decisions affect task completion and system performance.  
- Abstract concepts in OS theory map to practical, code-based implementations.  

---

## Extensions and Future Work

- Implementing multiple scheduling policies (e.g., Earliest Deadline First, Priority Scheduling).  
- Adding preemption to handle tasks that arrive during execution.  
- Modeling multi-core or distributed processors.  
- Measuring performance metrics such as average lateness, processor utilization, or total value achieved.  

