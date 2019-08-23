import { expect } from "chai";
import { shallowMount } from "@vue/test-utils";
import Logo from "@/components/Logo.vue";

describe("Logo.vue", () => {
  it("renders props.msg when passed", () => {
    const msg = "new message";
    const wrapper = shallowMount(Logo, {
      propsData: { msg }
    });
    expect(wrapper.text()).to.include(msg);
  });
});
