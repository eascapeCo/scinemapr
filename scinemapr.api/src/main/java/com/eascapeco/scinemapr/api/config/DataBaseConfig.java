package com.eascapeco.scinemapr.api.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"com.eascapeco.scinemapr.api.dao"})
@EnableTransactionManagement
public class DataBaseConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
        // mybatis 설정
        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setJdbcTypeForNull(JdbcType.VARCHAR);
        config.setAggressiveLazyLoading(true);
        config.setUseGeneratedKeys(false);
        config.setDefaultStatementTimeout(25000);
        config.setMapUnderscoreToCamelCase(true);
        config.setUseColumnLabel(true);

        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(datasource);
        sqlSessionFactory.setTypeAliasesPackage("com.eascapeco.scinemapr.api.model");
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
        sqlSessionFactory.setConfiguration(config);

        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    @Bean(name = "scTransactionManager")
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "scTransactionAttributeSource")
    public TransactionAttributeSource transactionAttributeSource() {
        // 트랜젝션 속성 설정
        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));

        RuleBasedTransactionAttribute tranAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
        tranAttribute.setTimeout(10);

        // method 패턴별 트랜젝션 설정
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("*", tranAttribute);

        return source;
    }

    /**
     * 트랜젝션 인터셉터 설정 - 일반적인 txAdvice에 포함되는 내용임 - 서비스의 method이름을 기반으로 트랜젝션의 적용을
     * 결정함
     * @return
     */
    @Bean(name = "scTransactionInterceptor")
    public TransactionInterceptor transactionInterceptor(
            @Qualifier("scTransactionAttributeSource") TransactionAttributeSource transactionAttributeSource,
            @Qualifier("scTransactionManager") PlatformTransactionManager transactionManager) {
        // 초기화
        TransactionInterceptor txAdvice = new TransactionInterceptor();

        // 설정한 속성 지정 및 transactionMAnager 지정
        txAdvice.setTransactionAttributeSource(transactionAttributeSource);
        txAdvice.setTransactionManager(transactionManager);

        return txAdvice;
    }

    /**
     * 실제 서비스 포인트컷에 트랜젝션을 적용하는 advice
     * @return
     */
    @Bean(name = "scServicePointCutTransactionAdvisor")
    public Advisor servicePointCutTransactionAdvisor(
            @Qualifier("scTransactionInterceptor") TransactionInterceptor transactionInterceptor
    ) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

        pointcut.setExpression("execution(* com.eascapeco.scinemapr..*Service.*(..))");

        return new DefaultPointcutAdvisor(pointcut, transactionInterceptor);
    }

    @Bean("jasyptStringEncrptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("zmfnWkd!23");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        return encryptor;
    }
}

