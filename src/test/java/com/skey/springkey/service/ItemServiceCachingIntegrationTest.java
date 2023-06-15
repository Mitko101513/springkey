package com.skey.springkey.service;

import com.skey.springkey.SpringkeyApplication;
import com.skey.springkey.config.RedisConfig;
import com.skey.springkey.model.Item;
import com.skey.springkey.repository.ItemRepository;
import com.skey.springkey.service.impl.ItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ContextConfiguration(classes = SpringkeyApplication.class)
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@Import({ RedisConfig.class})
@ExtendWith(SpringExtension.class)
@EnableCaching
@ImportAutoConfiguration(classes = {
        CacheAutoConfiguration.class,
        RedisAutoConfiguration.class
})
public class ItemServiceCachingIntegrationTest {

    private static final String AN_ID = "id-1";
    private static final String A_DESCRIPTION = "an item";

    @MockBean
    private ItemRepository mockItemRepository;

    @Autowired
    private ItemServiceImpl itemServiceImpl;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void givenRedisCaching_whenFindItemById_thenItemReturnedFromCache() {
        Item anItem = new Item(AN_ID, A_DESCRIPTION);
        Optional<Item> optionalItem = Optional.of(anItem);
        given(mockItemRepository.findById(AN_ID))
                .willReturn(optionalItem);

        Item itemCacheMiss = itemServiceImpl.getItemForId(AN_ID);
        Item itemCacheHit = itemServiceImpl.getItemForId(AN_ID);

        assertThat(itemCacheMiss).isEqualTo(anItem);
        assertThat(itemCacheHit).isEqualTo(anItem);

//        verify(mockItemRepository, times(1)).findById(AN_ID);
        assertThat(itemFromCache()).isEqualTo(anItem);
    }

    private Object itemFromCache() {
        Object cacheObj = cacheManager.getCache("itemCache").get(AN_ID).get();
        return cacheObj;
    }
}
