package com.finalproj.demo.repository;

import com.finalproj.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    Optional<Student> findStudentByEmail(String email);



//    @Repository
//    public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long>, ITransactionLogRepository {
//
//        @Query("SELECT log.clientId as clientId, log.merchantId as merchantId, log.transactionType as transactionType, log.currency as currency, " +
//                "sum(log.amount) as grossAmount, count(log.amount) as totalNumber " +
//                "from TransactionLog log " +
//                "where log.settlementDate >= :startDate and log.settlementDate <= :endDate " +
//                "and log.responseCode in ('00', '08') and lower(log.transactionType) in ('purchase', 'completion', 'refund') " +
//                "and lower(log.cardDescription) not in ('diners', 'amex', 'jcb') " +
//                "group by log.clientId, log.merchantId, log.transactionType, log.currency")
//        List<TxSummaryFromLog> findAllTxSummaries(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
//    }

}
